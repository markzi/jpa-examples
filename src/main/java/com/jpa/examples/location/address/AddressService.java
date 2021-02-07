package com.jpa.examples.location.address;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Needs @Transactional because we are streaming the results back
     *
     * @return
     */
    @Transactional
    public List<AddressResponse> findAll() {
        return addressRepository.findAll().stream().map(address -> AddressResponse.convert.apply(address)).collect(Collectors.toList());
    }
}
