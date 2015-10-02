package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Need.class)
public class NeedEventHandler {
    @Autowired
    private NeedSearchRepository needSearchRepository;

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
