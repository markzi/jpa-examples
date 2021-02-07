package com.jpa.examples.location.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

//    @Query("select AddressEntity from AddressEntity")
//    Stream<AddressEntity> streamFindAll();
}
