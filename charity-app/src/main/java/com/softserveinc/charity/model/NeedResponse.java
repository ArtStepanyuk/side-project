package com.softserveinc.charity.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.support.ResponseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "need_response", uniqueConstraints = @UniqueConstraint(columnNames={"need_id", "user_id"}))
public class NeedResponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private Date created;

    /* Do not put lazy fetch case needResponses/1/need will fail (https://jira.spring.io/browse/DATAJPA-630) */
    @ManyToOne
    @JoinColumn(name = "need_id")
    @JsonIgnoreProperties({"needResponses", "userCreated"})
    private Need need;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private ResponseStatus status = ResponseStatus.NEW;

    @JsonGetter
    public Integer getUserId() {
        return this.user != null ? user.getId() : null;
    }

    @Enumerated(EnumType.ORDINAL)
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    /**
     * Access Methods
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
