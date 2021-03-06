package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@Document(indexName = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "cities")
public class City implements Serializable{
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;

    /**
     * Do not put Lazy fetch
     */
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "regions")
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
