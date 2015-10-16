package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.exceptions.CategoryHasChildrenException;
import com.softserveinc.charity.exceptions.CategoryNotPresentException;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RepositoryEventHandler(Need.class)
public class NeedEventHandler {
    @Autowired
    private NeedSearchRepository needSearchRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleBeforeSaveOrCreate(Need need){
        Optional.ofNullable(need.getCategory())
                .filter(category -> category != null)
                .orElseThrow(CategoryNotPresentException::new);

        Optional.ofNullable(need.getCategory().getChildren())
                .filter(List::isEmpty)
                .orElseThrow(CategoryHasChildrenException::new);
    }

    @HandleAfterSave
    public void handleAfterSave(Need need){
        needSearchRepository.save(Converter.convert(need));
    }

    @HandleAfterCreate
    public void handleAfterCreate(Need need){
        needSearchRepository.save(Converter.convert(need));
    }

    @HandleAfterDelete
    public void handleAfterDelete(Need need){
        needSearchRepository.delete(Converter.convert(need));
    }
}
