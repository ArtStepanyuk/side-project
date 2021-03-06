package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.model.offer.Offer;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import com.softserveinc.charity.validator.OfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(Offer.class)
public class OfferEventHandler {
    @Autowired
    private OfferSearchRepository offerSearchRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleBeforeSaveOrCreate(Offer offer){
        OfferValidator.validate(offer);
    }

    @HandleAfterSave
    public void handleAfterSave(Offer offer){
        offerSearchRepository.save(Converter.convert(offer));
    }

    @HandleAfterCreate
    public void handleAfterCreate(Offer offer){
        offerSearchRepository.save(Converter.convert(offer));
    }

    @HandleAfterDelete
    public void handleAfterDelete(Offer offer){
        offerSearchRepository.delete(Converter.convert(offer));
    }
}
