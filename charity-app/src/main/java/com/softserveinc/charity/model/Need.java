package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "needs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Need implements Serializable{
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("d MMMM yyyy");

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne
    private City city;

    @Column
    private String address;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column
    @JsonIgnore
    private LocalDate actualTo;

    @Column
    private String convenientTime;

    @Column
    private Boolean pickup;

    @OneToOne
    private User userCreated;

    @OneToOne
    private Category category;

    @Column
    private Date created;

    @Column
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @JsonGetter
    public String getFormattedActualTo() {
        return this.actualTo != null ? DATE_TIME_FORMATTER.print(this.actualTo) : null;
    }

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

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
