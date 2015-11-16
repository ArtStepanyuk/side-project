package com.softserveinc.charity.model.offer;

import com.softserveinc.charity.model.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "inLine", types = {Offer.class})
public interface OfferInLineProjection {
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

    boolean getOpen();

    Set<Image> getImages();
}
