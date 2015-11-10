package com.softserveinc.charity.docs;

import com.softserveinc.charity.Application;
import com.softserveinc.charity.config.AppConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by venelinspiridonov on 10/19/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApiDocumentationTest {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/api").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index", links(halLinks(),
                        linkWithRel("images").description("description"),
                        linkWithRel("offers").description("description1"),
                        linkWithRel("needs").description("description2"),
                        linkWithRel("need_response").description("description3"),
                        linkWithRel("offer_response").description("description3"),
                        linkWithRel("categories").description("description4"),
                        linkWithRel("/api/category/root").description("description5"),
                        linkWithRel("addresses").description("description6"),
                        linkWithRel("regions").description("description7"),
                        linkWithRel("cities").description("description8"),
                        linkWithRel("users").description("description9"),
                        linkWithRel("/api/search/needs").description("description10"),
                        linkWithRel("/api/search/offers").description("description11"),
                        linkWithRel("/api/users/current").description("description12"),
                        linkWithRel("profile").description("description13"),
                        linkWithRel("userroles").description("description14")
                        )));

    }

    @Test
    public void users() throws Exception {
        this.mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("users"));
    }

    @Test
    public void needs() throws Exception {
        this.mockMvc.perform(get("/api/needs?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("needs"));
    }

    @Test
    public void cities() throws Exception {
        this.mockMvc.perform(get("/api/cities?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("cities"));
    }

    @Test
    public void categories() throws Exception {
        this.mockMvc.perform(get("/api/categories?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("categories"));
    }

    @Test
    public void categoryRoot() throws Exception {
        this.mockMvc.perform(get("/api/category/root").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("category/root"));
    }

    @Test
    public void offers() throws Exception {
        this.mockMvc.perform(get("/api/offers?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("offers"));
    }

    @Test
    public void user_roles() throws Exception {
        this.mockMvc.perform(get("/api/userroles?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("user_roles"));
    }

    @Test
    public void images() throws Exception {
        this.mockMvc.perform(get("/api/images?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("images"));
    }

    @Test
    public void need_response() throws Exception {
        this.mockMvc.perform(get("/api/needResponses?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("need_response"));
    }

    @Test
    public void addresses() throws Exception {
        this.mockMvc.perform(get("/api/addresses?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("addresses"));
    }

    @Test
    public void regions() throws Exception {
        this.mockMvc.perform(get("/api/regions?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("regions"));
    }

    @Test
    public void offer_response() throws Exception {
        this.mockMvc.perform(get("/api/offerResponses?1,1,1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("offer_response"));
    }
     // ------ IGNORE SECTION
    @Ignore
    @Test
    public void api_search_needs() throws Exception {
        this.mockMvc.perform(get("/api/search/needs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("api_search_needs"));
    }

    @Ignore
    @Test
    public void api_search_offers() throws Exception {
        this.mockMvc.perform(get("/api/search/offers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("api_search_offers"));
    }

    @Ignore
    @Test
    public void api_users_current() throws Exception {
        this.mockMvc.perform(get("/api/users/current").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("api_users_current"));
    }
    // ------ END OF IGNORE SECTION
}
