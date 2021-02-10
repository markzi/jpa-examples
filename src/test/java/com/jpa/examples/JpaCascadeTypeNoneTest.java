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
public class JpaCascadeTypeNoneTest {

    @Autowired
    private JpaCascadeTypeNoneRepository repository;

    @Autowired
    private TownRepository townRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void givenNoCascadeType_whenSavingAggregate_thenErrorForNonNullIdColumn() {

        TownEntity townEntity = new TownEntity();
        townEntity.setName("test town");

        JpaCascadeTypeNoneEntity addressEntity = new JpaCascadeTypeNoneEntity();
        addressEntity.setFirstLine("test save");
        addressEntity.setSecondLine("test save");
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode("test save");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.save(addressEntity));
    }

    @Test
    public void givenNoCascadeType_whenUpdatingAggregateObjectValue_thenAggregateObjectValueNotChanged() {

        String value = "jpa_cascade_type_none_update";

        TownEntity townEntity = new TownEntity();
        townEntity.setName(value);
        townRepository.save(townEntity);

        JpaCascadeTypeNoneEntity addressEntity = new JpaCascadeTypeNoneEntity();
        addressEntity.setFirstLine(value);
        addressEntity.setSecondLine(value);
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode(value);

        // save the address
        repository.save(addressEntity);

        addressEntity.setFirstLine("changed");
        addressEntity.getTown().setName("changed");

        // save changed address
        JpaCascadeTypeNoneEntity addressEntity1 = repository.save(addressEntity);

        // get address which contains the same town
        JpaCascadeTypeNoneEntity addressEntity2 = repository.findById(addressEntity.getId()).get();

        Assertions.assertAll(

                // check address did have a town
                () -> Assertions.assertNotNull(addressEntity.getTown()),

                // check address first line was changed
                () -> Assertions.assertEquals("changed", addressEntity2.getFirstLine()),

                // check both town entities still have the same name
                () -> Assertions.assertEquals(addressEntity1.getTown().getName(), addressEntity2.getTown().getName())

        );
    }

    @Test
    public void givenNoCascadeType_whenDeletingAggregate_thenSharedEntityStillExists() {

        String value = "jpa_cascade_type_persist_update";

        TownEntity townEntity = new TownEntity();
        townEntity.setName(value);

        // save the town
        townRepository.save(townEntity);

        // save the address 1
        JpaCascadeTypeNoneEntity addressEntity1 = saveAddress(value, townEntity);

        // save the address 2
        JpaCascadeTypeNoneEntity addressEntity2 = saveAddress(value, townEntity);

        // delete address 1
        repository.delete(addressEntity1);

        // get address which contains the same town
        JpaCascadeTypeNoneEntity addressEntity3 = repository.findById(addressEntity2.getId()).get();

        // get town
        TownEntity townEntity1 = townRepository.findById(addressEntity3.getTown().getId()).get();

        Assertions.assertAll(

                // check address 1 did have a town
                () -> Assertions.assertNotNull(addressEntity1.getTown()),

                // check address 1 was deleted
                () -> Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(addressEntity1.getId()).get()),

                // check both adress 1 and 2 did have the same town
                () -> Assertions.assertEquals(addressEntity1.getTown().getId(), addressEntity3.getTown().getId())

        );
    }

    private JpaCascadeTypeNoneEntity saveAddress(String value, TownEntity townEntity) {
        JpaCascadeTypeNoneEntity addressEntity = new JpaCascadeTypeNoneEntity();
        addressEntity.setFirstLine(value);
        addressEntity.setSecondLine(value);
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode(value);

        return repository.save(addressEntity);
    }
}
