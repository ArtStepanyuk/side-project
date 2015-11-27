package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Document(indexName = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "categories")
public class Category implements Serializable {
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties(value = "children")
    private Category parent;

    @Column
    @JsonIgnore
    private Integer lvl;

    @Column
    @JsonIgnore
    private Integer maxLvl;

    @OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "categories")
    @JsonIgnoreProperties(value = "parent")
    public List<Category> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Integer getMaxLvl() {
        return maxLvl;
    }

    public void setMaxLvl(Integer maxLvl) {
        this.maxLvl = maxLvl;
    }
}
