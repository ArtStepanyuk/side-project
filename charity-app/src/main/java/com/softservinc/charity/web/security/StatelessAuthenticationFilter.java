package com.softservinc.charity.web.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StatelessAuthenticationFilter extends GenericFilterBean implements Filter {

	private final TokenAuthenticationService tokenAuthenticationService;

	protected StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		SecurityContextHolder.getContext().setAuthentication(
				tokenAuthenticationService.getAuthentication((HttpServletRequest) req));
		chain.doFilter(req, res); // always continue
	}
}