package com.softserveinc.charity.model;

import com.softserveinc.charity.model.support.ResponseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "needs_responses")
public class NeedResponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private Date created; //TODO: ignore on update(PUT)

    /* Do not put lazy fetch case needResponses/1/need will fail (https://jira.spring.io/browse/DATAJPA-630) */
    @ManyToOne
    @JoinColumn(name = "need_id")
    private Need need;

    @ManyToOne
    private User user;

    @Column
    private ResponseStatus responseStatus; //TODO: show only not DELETED

    @Enumerated(EnumType.ORDINAL)
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    /** Access Methods */
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
