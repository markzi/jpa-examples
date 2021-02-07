package com.jpa.examples.location.town;

import com.jpa.examples.location.address.AddressEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(schema = "location", name = "town")
public class TownEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "town")
    private Set<AddressEntity> address;
}