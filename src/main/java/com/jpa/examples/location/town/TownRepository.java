package com.jpa.examples.location.town;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface TownRepository extends JpaRepository<TownEntity, Long> {

//    @Query(value = "select * from town", nativeQuery = true)
//    Stream<TownEntity> streamFindAll();
}
