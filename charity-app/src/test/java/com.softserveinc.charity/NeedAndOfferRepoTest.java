package com.softserveinc.charity;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class NeedAndOfferRepoTest extends AbstractWebIntegrationTest {

    @Test
    public void test_offers_get() throws Exception{
        this.mockMvc.perform(get("/api/offers"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.page.totalElements").value(9));
    }

    @Test
    public void test_needs_get() throws Exception{
        this.mockMvc.perform(get("/api/needs"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.page.totalElements").value(9));
    }
}
