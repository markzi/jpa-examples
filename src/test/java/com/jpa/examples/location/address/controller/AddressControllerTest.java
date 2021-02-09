package com.jpa.examples.location.address.controller;

import com.jpa.examples.location.address.AddressResponse;
import com.jpa.examples.location.address.AddressService;
import com.jpa.examples.location.address.controller.AddressController;
import com.jpa.examples.location.town.TownResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
@ContextConfiguration(classes = {AddressController.class})
class AddressControllerTest {

    @MockBean
    private AddressService addressService;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenId_whenFindById_thenAddressReturned() throws Exception {

        AddressResponse addressResponse = AddressResponse.builder()
                .postcode("NP")
                .firstLine("The North Pole")
                .secondLine("Very Cold")
                .town(TownResponse.builder().id(1L).firstLines(Arrays.asList("North Town")).build())
                .build();

        Mockito.when(addressService.findById(anyLong())).thenReturn(addressResponse);

        //the test
        mockMvc.perform(get("/address/1").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

}
