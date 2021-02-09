package com.jpa.examples.location.address;


import com.jpa.examples.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        return addressRepository.findAll().stream().map(address -> convert(address)).collect(Collectors.toList());
    }

    public AddressResponse findById(Long id) {
        return convert(findByIdFromRepository(id));
    }

    public AddressResponse delete(Long id) {
        AddressEntity addressEntity = findByIdFromRepository(id);
        addressRepository.delete(addressEntity);
        return convert(addressEntity);
    }

    private AddressEntity findByIdFromRepository(Long id) {
        return addressRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private AddressResponse convert(AddressEntity addressEntity) {
        return AddressResponse.convert.apply(addressEntity);
    }
}
