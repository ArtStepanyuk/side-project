package com.softserveinc.charity.model.need.projection;

import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.OfferResponse;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.offer.Offer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "inLine", types = {Offer.class})
public interface OfferInLine {
    Integer getId();

    City getCity();

    User getUserCreated();

    String getAddress();

    String getConvenientTime();

    Date getCreated();

    String getDescription();

    String getFormattedActualTo();

    String getName();

    Boolean getPickup();

    Date getUpdated();

    Category getCategory();

    Set<OfferResponse> getOfferResponses();
}
