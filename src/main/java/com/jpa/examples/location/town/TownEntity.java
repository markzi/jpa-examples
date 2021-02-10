package com.jpa.examples.location.town;

import com.jpa.examples.location.address.AddressEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "location", name = "town")
@Getter
@Setter
public class TownEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "town")
    private Set<AddressEntity> address;
}
