package com.softserveinc.charity.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    /**
     * Default ctor.
     */
    public Image() {
    }

    public Image(final String path) {
        this.path = path;
    }

    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String path;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
