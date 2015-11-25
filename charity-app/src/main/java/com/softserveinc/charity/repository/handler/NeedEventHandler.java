package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.validator.NeedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(Need.class)
public class NeedEventHandler {
    @Autowired
    private NeedSearchRepository needSearchRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleBeforeSaveOrCreate(Need need){
        NeedValidator.validate(need);
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
