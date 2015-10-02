package com.softserveinc.charity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NeedAndOfferRepoTest extends AbstractWebIntegrationTest {

    @Autowired
    RepositoryEntityLinks entityLinks;


    @Before
    public void setup(){}

    @Test
    public void test1_offers_get() throws Exception{
        this.mockMvc.perform(get("/api/offers"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.page.totalElements").value(9));
    }

    @Test
    public void test2_needs_get() throws Exception{
        this.mockMvc.perform(get("/api/needs"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.page.totalElements").value(9));
    }

    @Test
    public void test3_needs_post() throws Exception{

        this.mockMvc.perform(post("/api/needs").content("{\"name\":\"some like it hot\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void test4_offers_post() throws Exception{

        this.mockMvc.perform(post("/api/offers").content("{\"name\":\"some like it hot\"}"))
                .andExpect(status().isCreated());
    }
}
