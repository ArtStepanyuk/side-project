package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "regions")
@Document(indexName = "regions", type = "region", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "fs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    //@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    //@JsonManagedReference
    @JsonIgnore
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
