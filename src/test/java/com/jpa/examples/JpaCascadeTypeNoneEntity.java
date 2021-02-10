package com.jpa.examples;

import com.jpa.examples.location.town.TownEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "location", name = "address")
@Getter
@Setter
public class JpaCascadeTypeNoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_line")
    private String firstLine;

    @Column(name = "second_line")
    private String secondLine;

    @Column(name = "third_line")
    private String thirdLine;

    @Column(name = "postcode")
    private String postcode;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private TownEntity town;

}
