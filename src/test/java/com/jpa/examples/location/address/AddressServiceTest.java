package com.jpa.examples.location.address;

import com.jpa.examples.location.town.TownEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @TestConfiguration
    public static class EmployeeServiceImplTestContextConfiguration {

        @MockBean
        private AddressRepository addressRepository;

        @Bean
        public AddressService addressService() {
            return new AddressService(addressRepository);
        }
    }

    @Test
    public void given_findById_whenIdIsOne_thenAddressResponseReturned() {

        TownEntity townEntity = new TownEntity();
        townEntity.setId(1L);
        townEntity.setName("North Town");

        AddressEntity northPole = new AddressEntity();
        northPole.setId(1L);
        northPole.setPostcode("NP");
        northPole.setFirstLine("The North Pole");
        northPole.setSecondLine("Very Cold");
        northPole.setTown(townEntity);

        Mockito.when(addressRepository.findById(1L))
                .thenReturn(Optional.of(northPole));

        AddressResponse addressResponse = addressService.findById(1L);

        Assertions.assertAll(
                () -> Assertions.assertEquals("The North Pole", addressResponse.getFirstLine()),
                () -> Assertions.assertEquals("Very Cold", addressResponse.getSecondLine()),
                () -> Assertions.assertEquals("NP", addressResponse.getPostcode()),
                () -> Assertions.assertEquals(1L, addressResponse.getTown().getId())
        );
    }

}
