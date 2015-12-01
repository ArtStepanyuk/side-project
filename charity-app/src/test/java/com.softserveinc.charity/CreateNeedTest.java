package com.softserveinc.charity;


import com.softserveinc.charity.facade.NeedFacade;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserAuthentication;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;
import com.softserveinc.charity.security.service.UserDetailsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;

public class CreateNeedTest extends AbstractWebIntegrationTest {

    private NeedRequestData data;

    @Autowired
    private NeedFacade needFacade;

    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void setup(){
        data = new NeedRequestData();
        data.setName("name");
        data.setAddress("address");
        data.setCategories("clothes,child's,outerwear");
        data.setCity("{\"name\":\"Ivano-Frankovsk\",\"region\":{\"id\":9,\"name\":\"Ivano-Frankovskaja oblast\"}");
        final User authenticatedUser = userDetailsService.loadUserByUsername("user@gmail.com");
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }

    @Test
    public void createNeed(){
        Need need = needFacade.save(data, new HashMap<>());
        Assert.assertEquals(data.getName(), need.getName());
    }
}
