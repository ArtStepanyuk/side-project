package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@Document(indexName = "cities", type = "city", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "fs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class City implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    //@JsonBackReference
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
