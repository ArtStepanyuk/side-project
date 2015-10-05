package com.softserveinc.charity.repository.converter;

import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.model.offer.Offer;
import com.softserveinc.charity.model.offer.OfferDetails;

public class Converter {

    //TODO remove duplicated code, create on details class

    public static NeedDetails convert(Need need){
        NeedDetails needDetails = new NeedDetails();
        needDetails.setId(need.getId());
        needDetails.setActualTo(need.getActualTo());
        needDetails.setAddress(need.getAddress());
        needDetails.setCategory(need.getCategory());
        needDetails.setCity(need.getCity());
        needDetails.setConvenientTime(need.getConvenientTime());
        needDetails.setDescription(need.getDescription());
        needDetails.setName(need.getName());
        needDetails.setUserCreated(need.getUserCreated());
        needDetails.setPickup(need.getPickup());
        needDetails.setCreated(need.getCreated());
        needDetails.setUpdated(need.getUpdated());
        needDetails.setNeedResponses(need.getNeedResponses());
        return needDetails;
    }

    public static OfferDetails convert(Offer offer){
        OfferDetails offerDetails = new OfferDetails();
        offerDetails.setId(offer.getId());
        offerDetails.setActualTo(offer.getActualTo());
        offerDetails.setAddress(offer.getAddress());
        offerDetails.setCategory(offer.getCategory());
        offerDetails.setCity(offer.getCity());
        offerDetails.setConvenientTime(offer.getConvenientTime());
        offerDetails.setDescription(offer.getDescription());
        offerDetails.setName(offer.getName());
        offerDetails.setUserCreated(offer.getUserCreated());
        offerDetails.setImages(offer.getImages());
        offerDetails.setPickup(offerDetails.getPickup());
        offerDetails.setCreated(offer.getCreated());
        offerDetails.setUpdated(offer.getUpdated());
        offerDetails.setOfferResponses(offer.getOfferResponses());
        return offerDetails;
    }
}
