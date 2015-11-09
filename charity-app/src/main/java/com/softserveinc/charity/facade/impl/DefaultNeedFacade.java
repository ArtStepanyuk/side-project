package com.softserveinc.charity.facade.impl;

import com.softserveinc.charity.facade.NeedFacade;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;
import com.softserveinc.charity.service.NeedService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DefaultNeedFacade implements NeedFacade {

    @Autowired
    NeedService needService;


    @Override
    public Need save(final NeedRequestData requestData) {

        Assert.notNull(requestData, "Request Data can not be null");
        final Need need = new Need();

        need.setName(requestData.getName());
        need.setDescription(requestData.getDescription());
        need.setAddress(requestData.getAddress());
        need.setPickup(requestData.getPickup());
        need.setConvenientTime(requestData.getConvenientTime());
        need.setActualTo(new LocalDate(requestData.getTopicality()));

        needService.save(need);
                //categories and city


        return need;
    }
}
