package com.softserveinc.charity.model.need;

import com.softserveinc.charity.model.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "inLine", types = {Need.class})
public interface NeedInLineProjection {
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

    boolean getOpen();

    Set<Image> getImages();
}
