package com.jpa.examples.location.address;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

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
}
