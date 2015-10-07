package com.softserveinc.charity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.charity.model.Address;
import com.softserveinc.charity.model.TestUser;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.repository.jpa.UserRepository;
import com.softserveinc.charity.util.Constants;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
//@Ignore
public class RegistrationFlowTest extends AbstractWebIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private TestUser user;

    private String json;

    @Before
    public void init() {
        user = new TestUser();
        user.setUsername("test@gmail.com");
        user.setPassword("test");
        user.setName("testushka");
        user.setAddress(new Address());
        try {
            json = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            //
        }
    }

    @Test
    @Ignore
    public void login_existing_account_test() throws Exception{
        MvcResult mvcResult = this.mockMvc
                .perform(post("/api/auth")
                        .content("{\"username\":\"user@gmail.com\",\"password\":\"user\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String token = mvcResult.getResponse().getHeader(Constants.AUTH_HEADER_NAME);
        Assert.assertNotNull(token);
        Assert.assertTrue(token.length() > 0 && token.length() < 170);

        ResultActions resultActions = this.mockMvc.perform(get("/api/users/current")
                .header(Constants.AUTH_HEADER_NAME, token));

        mvcResult = resultActions.andReturn();

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.username").value("user@gmail.com"));
    }

    @Test
    public void register1_with_new_account_test_to_custom_endpoint() throws Exception {
        this.mockMvc
                .perform(post("/api/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.mockMvc
                .perform(post("/api/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        MvcResult mvcResult = this.mockMvc
                .perform(post("/api/auth")
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String token = mvcResult.getResponse().getHeader(Constants.AUTH_HEADER_NAME);
        Assert.assertNotNull(token);

        //Thread.sleep(1000);

        this.mockMvc.perform(get("/api/users/current")
                .header(Constants.AUTH_HEADER_NAME, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

    @After
    public void close(){
        User user1 = userRepository.findByEmail("test@gmail.com");
        if (user1 != null)
            userRepository.delete(user1.getId());
    }

}
