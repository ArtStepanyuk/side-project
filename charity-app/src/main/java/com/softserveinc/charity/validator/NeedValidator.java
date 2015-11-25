package com.softserveinc.charity.validator;

import com.softserveinc.charity.exceptions.CategoryHasChildrenException;
import com.softserveinc.charity.exceptions.CategoryNotPresentException;
import com.softserveinc.charity.model.need.Need;

import java.util.List;
import java.util.Optional;

public class NeedValidator {

    public static void validate(Need need) throws CategoryNotPresentException, CategoryHasChildrenException{
        Optional.ofNullable(need.getCategory())
                .filter(category -> category != null)
                .orElseThrow(CategoryNotPresentException::new);

        Optional.ofNullable(need.getCategory().getChildren())
                .filter(List::isEmpty)
                .orElseThrow(CategoryHasChildrenException::new);
    }
}
