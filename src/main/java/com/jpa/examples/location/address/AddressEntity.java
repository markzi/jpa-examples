package com.jpa.examples.location.address;

import com.jpa.examples.location.town.TownEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(schema = "location", name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "first_line")
    private String firstLine;

    @Column(name = "second_line")
    private String secondLine;

    @Column(name = "third_line")
    private String thirdLine;

    @Column(name = "postcode")
    private String postcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id")
    private TownEntity town;
}