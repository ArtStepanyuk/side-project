package com.softserveinc.charity.security.service;

import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserAuthentication;
import com.softserveinc.charity.repository.UserRepository;
import com.softserveinc.charity.security.util.TokenHandler;
import com.softserveinc.charity.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@Service
@PropertySource("classpath:security.properties")
public class TokenAuthenticationService {

	private final TokenHandler tokenHandler;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + Constants.TEN_DAYS);
		response.addHeader(Constants.AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(Constants.AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
