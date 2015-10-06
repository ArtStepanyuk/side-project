package com.softserveinc.charity.repository.projection;

import com.softserveinc.charity.model.NeedResponse;
import com.softserveinc.charity.model.Offer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inLine", types = {Offer.class})
public interface OfferInLine {
    Integer getId();



}
