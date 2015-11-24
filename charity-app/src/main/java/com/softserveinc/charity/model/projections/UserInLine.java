package com.softserveinc.charity.model.projections;

import com.softserveinc.charity.model.*;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.offer.Offer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "inLine", types = {User.class})
public interface UserInLine {

    Set<UserRole> getRoles();

    String getToken();

    long getExpires();

    String getUsername();

    String getEmail();

    String getFirstName();

    String getLastName();

    Address getAddress();

    @Value("#{target.address.city}")
    City getCity();

    Set<Need> getNeeds();

    Set<NeedResponse> getNeedResponses();

    Set<Offer> getOffers();

    Set<OfferResponse> getOfferResponses();
}
