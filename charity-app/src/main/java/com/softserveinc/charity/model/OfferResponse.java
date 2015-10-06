package com.softserveinc.charity.model;

import com.softserveinc.charity.model.support.ResponseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for offer responses.
 */
@Entity
@Table(name = "offers_responses", uniqueConstraints = @UniqueConstraint(columnNames={"offer_id", "user_id"}))
public class OfferResponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private Date created;

    /* Do not put lazy fetch case offerResponses/1/offer will fail (https://jira.spring.io/browse/DATAJPA-630) */
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    private User user;

    @Column
    private ResponseStatus status = ResponseStatus.NEW;

    /**
     * Status saved as int value (0 -> NEW, 1 -> DELETED, 2 -> APPROVED).
     * @return offer status as {@link ResponseStatus} object.
     */
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

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer need) {
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
