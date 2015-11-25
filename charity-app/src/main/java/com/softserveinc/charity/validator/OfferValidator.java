package com.softserveinc.charity.validator;

import com.softserveinc.charity.exceptions.CategoryHasChildrenException;
import com.softserveinc.charity.exceptions.CategoryNotPresentException;
import com.softserveinc.charity.model.offer.Offer;

import java.util.List;
import java.util.Optional;

public class OfferValidator {

    public static void validate(Offer offer) throws CategoryNotPresentException, CategoryHasChildrenException{
        Optional.ofNullable(offer.getCategory())
                .filter(category -> category != null)
                .orElseThrow(CategoryNotPresentException::new);

        Optional.ofNullable(offer.getCategory().getChildren())
                .filter(List::isEmpty)
                .orElseThrow(CategoryHasChildrenException::new);
    }
}
