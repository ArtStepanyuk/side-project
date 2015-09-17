package com.softserveinc.charity.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToOne
    private Category parent;

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
}
