package com.softservinc.charity.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "regions")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private Set<City> cities;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        List<City> cities = new ArrayList<City>();
        cities.addAll(this.cities);
        return cities;
    }
}
