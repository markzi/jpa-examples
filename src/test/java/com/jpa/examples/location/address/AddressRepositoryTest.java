package com.jpa.examples.location.address;

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

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void givenAddress1Id_whenFindById_thenAddres1Returned() {
        AddressEntity addressResponse = addressRepository.findById(1l).get();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1l, addressResponse.getId()),
                () -> Assertions.assertEquals(1l, addressResponse.getTown().getId())
        );
    }

    @Test
    public void givenAddress_whenSaving_thenDatabaseAddressSaved() {

        TownEntity townEntity = townRepository.findById(1L).get();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setFirstLine("test save");
        addressEntity.setSecondLine("test save");
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode("test save");

        AddressEntity addressResponse = addressRepository.save(addressEntity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(addressResponse.getId()),
                () -> Assertions.assertEquals("test save", addressResponse.getFirstLine()),
                () -> Assertions.assertEquals("test save", addressResponse.getSecondLine()),
                () -> Assertions.assertEquals("test save", addressResponse.getPostcode()),
                () -> Assertions.assertEquals(1L, addressResponse.getTown().getId())
        );

        AddressEntity test = entityManager
                .createQuery("SELECT a FROM AddressEntity a WHERE a.id =:id", AddressEntity.class)
                .setParameter("id", addressResponse.getId())
                .getSingleResult();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(test.getId()),
                () -> Assertions.assertEquals("test save", test.getFirstLine()),
                () -> Assertions.assertEquals("test save", test.getSecondLine()),
                () -> Assertions.assertEquals("test save", test.getPostcode()),
                () -> Assertions.assertEquals(1L, test.getTown().getId())
        );
    }
    @Test
    public void givenAddressTownWithNotCascadePersist_whenSaving_thenDatabaseAddressSavedButTownIsNot() {

        TownEntity townEntity = new TownEntity();
        townEntity.setName("test town");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setFirstLine("test save");
        addressEntity.setSecondLine("test save");
        addressEntity.setTown(townEntity);
        addressEntity.setPostcode("test save");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> addressRepository.save(addressEntity));
    }
}
