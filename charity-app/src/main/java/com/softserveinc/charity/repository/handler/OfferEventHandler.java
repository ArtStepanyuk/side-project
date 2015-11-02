package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.exceptions.CategoryHasChildrenException;
import com.softserveinc.charity.exceptions.CategoryNotPresentException;
import com.softserveinc.charity.model.offer.Offer;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RepositoryEventHandler(Offer.class)
public class OfferEventHandler {
    @Autowired
    private OfferSearchRepository offerSearchRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleBeforeSaveOrCreate(Offer offer){
        Optional.ofNullable(offer.getCategory())
                .filter(category -> category != null)
                .orElseThrow(CategoryNotPresentException::new);

        Optional.ofNullable(offer.getCategory().getChildren())
                .filter(List::isEmpty)
                .orElseThrow(CategoryHasChildrenException::new);
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
