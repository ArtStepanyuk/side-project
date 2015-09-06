package com.softservinc.charity.config;

import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;


/**
 * Configures mockMvc for using servletPath as dispatcher entry point in web.xml, but in tests.
 */
public class ServletPathConfigurer implements MockMvcConfigurer {

    private final String servletPath;

    public ServletPathConfigurer(String servletPath) {
        this.servletPath = servletPath;
    }

    @Override
    public void afterConfigurerAdded(ConfigurableMockMvcBuilder<?> builder) {
        //
    }

    @Override
    public RequestPostProcessor beforeMockMvcCreated(
            ConfigurableMockMvcBuilder<?> builder,
            WebApplicationContext context) {

        return request -> {
            request.setServletPath(servletPath);
            return request;
        };
    }
}