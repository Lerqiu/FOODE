package com.example.foode.city.service;

import lombok.Data;

@Data
public class City {

    private Long id;

    private String name;

    public City() {
    }

    public City(Long id,
                String name) {
        this.id = id;
        this.name = name;
    }

}
