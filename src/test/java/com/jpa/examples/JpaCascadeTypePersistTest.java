package com.jpa.examples;

import com.jpa.examples.location.town.TownEntity;
import com.jpa.examples.location.town.TownRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class JpaCascadeTypePersistTest {

    @Autowired
    private JpaCascadeTypePersistRepository repository;

    @Autowired
    private TownRepository townRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void givenCascadeTypePersist_whenSavingAggregate_thenSaved() {

        TownEntity townEntity = new TownEntity();
        townEntity.setName("test town");

        JpaCascadeTypePersistEntity addressEntity = new JpaCascadeTypePersistEntity();
        addressEntity.setFirstLine("test save");
        addressEntity.setSecondLine("test save");
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode("test save");

        JpaCascadeTypePersistEntity jpaCascadeTypePersistEntity = repository.save(addressEntity);

        TownEntity townEntity1 = townRepository.findById(addressEntity.getTown().getId()).get();

        Assertions.assertAll(
                () -> Assertions.assertEquals("test save", jpaCascadeTypePersistEntity.getFirstLine()),
                () -> Assertions.assertEquals("test town", townEntity1.getName())
        );
    }

    @Test
    public void givenCascadeTypePersist_whenUpdatingAggregateObjectValue_thenAggregateObjectValueChanged() {

        String value = "jpa_cascade_type_persist_update";

        TownEntity townEntity = new TownEntity();
        townEntity.setName(value);
        townRepository.save(townEntity);

        JpaCascadeTypePersistEntity addressEntity = new JpaCascadeTypePersistEntity();
        addressEntity.setFirstLine(value);
        addressEntity.setSecondLine(value);
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode(value);

        // save the address
        repository.save(addressEntity);

        addressEntity.setFirstLine("changed");
        addressEntity.getTown().setName("changed");

        // save changed address
        JpaCascadeTypePersistEntity addressEntity1 = repository.save(addressEntity);

        // get address which contains the same town
        JpaCascadeTypePersistEntity addressEntity2 = repository.findById(addressEntity.getId()).get();

        Assertions.assertAll(

                // check address did have a town
                () -> Assertions.assertNotNull(addressEntity.getTown()),

                // check address first line was changed
                () -> Assertions.assertEquals("changed", addressEntity2.getFirstLine()),

                // check both town entities still have the same name
                () -> Assertions.assertEquals("changed", addressEntity2.getTown().getName())
        );
    }

    @Test
    public void givenCascadeTypePersist_whenDeletingAggregate_thenSharedEntityStillExists() {

        String value = "jpa_cascade_type_persist_update";

        TownEntity townEntity = new TownEntity();
        townEntity.setName(value);

        // save the town
        townRepository.save(townEntity);

        // save the address 1
        JpaCascadeTypePersistEntity addressEntity1 = saveAddress(value, townEntity);

        // save the address 2
        JpaCascadeTypePersistEntity addressEntity2 = saveAddress(value, townEntity);

        // delete address 1
        repository.delete(addressEntity1);

        // get address which contains the same town
        JpaCascadeTypePersistEntity addressEntity3 = repository.findById(addressEntity2.getId()).get();

        // get town
        TownEntity townEntity1 = townRepository.findById(addressEntity3.getTown().getId()).get();

        Assertions.assertAll(

                // check address 1 did have a town
                () -> Assertions.assertNotNull(addressEntity1.getTown()),

                // check address 1 was deleted
                () -> Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(addressEntity1.getId()).get()),

                // check both address 1 and 2 did have the same town
                () -> Assertions.assertEquals(addressEntity1.getTown().getId(), addressEntity3.getTown().getId())

        );
    }

    private JpaCascadeTypePersistEntity saveAddress(String value, TownEntity townEntity) {
        JpaCascadeTypePersistEntity addressEntity = new JpaCascadeTypePersistEntity();
        addressEntity.setFirstLine(value);
        addressEntity.setSecondLine(value);
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode(value);

        return repository.save(addressEntity);
    }
}
