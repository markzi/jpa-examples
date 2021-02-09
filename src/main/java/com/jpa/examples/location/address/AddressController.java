package com.jpa.examples.location.address;

import com.jpa.examples.location.address.AddressResponse;
import com.jpa.examples.location.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse findById(@PathVariable("id") Long id) {
        return addressService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse delete(@PathVariable("id") Long id) {
        return addressService.delete(id);
    }
}
