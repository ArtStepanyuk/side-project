package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@Document(indexName = "cities", type = "city", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "fs")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class City implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    @JsonIgnore // brakes internal reference loop in json
    @Field(type = FieldType.Nested)
    private Region region;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
