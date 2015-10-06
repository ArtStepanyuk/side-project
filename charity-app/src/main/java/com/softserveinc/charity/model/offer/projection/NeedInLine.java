package com.softserveinc.charity.model.offer.projection;

import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.NeedResponse;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.need.Need;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "inLine", types = {Need.class})
public interface NeedInLine {
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

    Set<NeedResponse> getNeedResponses();
}
