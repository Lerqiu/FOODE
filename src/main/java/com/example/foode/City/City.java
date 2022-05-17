package com.example.foode.City;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Data
public class City {

    @Id
    @SequenceGenerator(name = "city_id_seq", sequenceName = "city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(Long id,
                String name) {
        this.id = id;
        this.name = name;
    }
}
