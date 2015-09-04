package com.softservinc.charity.config;

import com.softservinc.charity.web.config.StatelessAuthenticationSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

@Configuration
@ImportResource({"classpath:appContext.xml"})
@Order(2)
public class TestConfig extends StatelessAuthenticationSecurityConfig {
}
