package com.softserveinc.charity.model.request;

import java.util.Date;

public class NeedRequestData {
    private String name;
    private String categories;
    private String description;
    private String city;
    private String address;
    private Date topicality;
    private String convenientTime;
    private Boolean pickup;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(final String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Date getTopicality() {
        return topicality;
    }

    public void setTopicality(final Date topicality) {
        this.topicality = topicality;
    }

    public String getConvenientTime() {
        return convenientTime;
    }

    public void setConvenientTime(final String convenientTime) {
        this.convenientTime = convenientTime;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(final Boolean pickup) {
        this.pickup = pickup;
    }
}
