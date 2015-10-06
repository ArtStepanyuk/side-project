package com.softserveinc.charity.model;

import com.softserveinc.charity.model.support.ResponseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "needs_responses", uniqueConstraints = @UniqueConstraint(columnNames={"need_id", "user_id"}))
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
    private Need need;

    @ManyToOne
    private User user;

    @Column
    private ResponseStatus status = ResponseStatus.NEW;

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
