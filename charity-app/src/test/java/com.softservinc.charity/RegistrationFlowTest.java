package com.softservinc.charity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softservinc.charity.model.TestUser;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
//@Ignore
public class RegistrationFlowTest extends WebTest{

    private TestUser user;

    private String json;

    @Before
    public void init() {
        user = new TestUser();
        user.setUsername("test@gmail.com");
        user.setPassword("test");
        user.setName("testushka");
        try {
            json = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            //
        }
    }

    @Test
    @Ignore
    public void login_existing_account_test() throws Exception{
        this.mockMvc
                .perform(post("/api/auth")
                        .content("{\"username\":\"user@gmail.com\",\"password\":\"user\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void register1_with_new_account_test() throws Exception {
        this.mockMvc
                .perform(post("/api/users/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void register2_with_existing_account_test() throws Exception {
        this.mockMvc
                .perform(post("/api/users/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void register3_get_current_account_test() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(post("/api/auth")
                        .content(json))
                .andReturn();

        String token = mvcResult.getResponse().getHeader(Constants.AUTH_HEADER_NAME);
        Assert.assertNotNull(token);
        Assert.assertEquals(169, token.length());

        //Thread.sleep(1000);

        this.mockMvc.perform(get("/api/users/current")
                .header(Constants.AUTH_HEADER_NAME, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.name").value(user.getName()));

    }
}
