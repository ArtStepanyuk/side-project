package com.softserveinc.charity.model.need;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.NeedResponse;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.support.ResponseStatus;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Basic class for need model.
 */
@NamedEntityGraph(name = "Need.detail", includeAllAttributes = true)
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseNeed implements Serializable {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("d MMMM yyyy");

    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne
    @Field(type = FieldType.Nested)
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

    @ManyToOne
    @Field(type = FieldType.Nested)
    @JoinColumn(name = "userCreated_id")
    private User userCreated;

    /* Do not put lazy fetch case needResponses/1/need will fail (https://jira.spring.io/browse/DATAJPA-630) */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "need", fetch = FetchType.EAGER)
    private Set<NeedResponse> needResponses;

    @OneToOne
    @Field(type = FieldType.Nested)
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

    @JsonGetter
    public boolean getOpen() {
        if (this.needResponses != null) {
            for (final NeedResponse response : needResponses) {
                if (response.getStatus().equals(ResponseStatus.APPROVED)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void setId(Integer id) {
        this.id = id;
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

    public Set<NeedResponse> getNeedResponses() {
        return needResponses;
    }

    public void setNeedResponses(Set<NeedResponse> needResponses) {
        this.needResponses = needResponses;
    }
}
