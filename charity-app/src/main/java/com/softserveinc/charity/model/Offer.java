package com.softserveinc.charity.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Image> images;

    @OneToOne
    private City city;

    @Column
    private String address;

    @Column
    private LocalDate actualTo;

    @Column
    private String convenientTime;

    @Column
    private Boolean pickup;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getActualTo() {
        return actualTo;
    }

    public void setActualTo(LocalDate actualTo) {
        this.actualTo = actualTo;
    }

    public String getConvenientTime() {
        return convenientTime;
    }

    public void setConvenientTime(String convenientTime) {
        this.convenientTime = convenientTime;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }
}
