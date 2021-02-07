package com.jpa.examples.location.service;

import com.jpa.examples.location.address.AddressService;
import com.jpa.examples.location.town.TownResponse;
import com.jpa.examples.location.town.TownService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final AddressService addressService;
    private final TownService townService;

    public LocationService(AddressService addressService, TownService townService) {
        this.addressService = addressService;
        this.townService = townService;
    }

    public List<LocationResponse> findAll() {
        return addressService.findAll().stream().map(addressResponse -> {
            TownResponse townResponse = townService.findById(addressResponse.getTownId());
            return LocationResponse.convert.apply(addressResponse, townResponse);
        }).collect(Collectors.toList());
    }
}
