package com.softserveinc.charity.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String path;
}
