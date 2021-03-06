package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "regions")
@Document(indexName = "region")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "regions")
public class Region implements Serializable {
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    /**
     * Do not put Lazy fetch
     */
    @OneToMany(mappedBy = "region")
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "cities")
    @JsonIgnore
    private List<City> cities;

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
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
