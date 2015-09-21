package com.softserveinc.charity.config;

import com.softserveinc.charity.security.filter.StatelessAuthenticationFilter;
import com.softserveinc.charity.security.filter.StatelessLoginFilter;
import com.softserveinc.charity.security.service.TokenAuthenticationService;
import com.softserveinc.charity.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Order(1)
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final int PASSWORD_STRENGTH = 11;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	Environment env;

	public StatelessAuthenticationSecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				//allow anonymous resource requests
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()

				//allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                //allow anonymous POSTs to register
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()

				//allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/api/**").permitAll()

				//defined Admin only API area
				.antMatchers("/admin/**").hasRole("ADMIN").and()
				.anonymous().and()
				.servletApi().and()
				.exceptionHandling().and()
				.headers().cacheControl();

		if (isSecurityAuthFiltersEnable()){
			http
					// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"}
					// which sets the token header upon authentication
					.addFilterBefore(new StatelessLoginFilter("/api/auth", tokenAuthenticationService,
							userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
					// custom Token based authentication based on the header previously given to the client
					.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
							UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }


	private Boolean isSecurityAuthFiltersEnable(){
		String value = env.getProperty("security.auth.filters.enable");
		if (value == null)
			return Boolean.TRUE;
		return Boolean.valueOf(value);
	}
}
