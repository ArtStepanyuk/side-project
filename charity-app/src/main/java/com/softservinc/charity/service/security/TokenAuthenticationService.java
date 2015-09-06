package com.softservinc.charity.service.security;

import com.softservinc.charity.Constants;
import com.softservinc.charity.model.User;
import com.softservinc.charity.service.UserService;
import com.softservinc.charity.util.security.TokenHandler;
import com.softservinc.charity.model.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@Service
public class TokenAuthenticationService {

	private final TokenHandler tokenHandler;

	@Autowired
	private UserService userService;

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
				userService.refresh(user);
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
