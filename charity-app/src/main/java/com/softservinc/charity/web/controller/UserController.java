package com.softservinc.charity.web.controller;

import com.softservinc.charity.Constants;
import com.softservinc.charity.facade.UserFacade;
import com.softservinc.charity.model.UserAuthentication;
import com.softservinc.charity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
public class UserController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger("com.softservinc.charity");

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/users/current", method = RequestMethod.GET)
    public User getCurrent(final HttpServletRequest request) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			User user =  ((UserAuthentication) authentication).getDetails();
			// TODO: Added token to user for UI integration. Remove after.
			user.setToken(request.getHeader(Constants.AUTH_HEADER_NAME));
			return user;
		}
		return new User(authentication.getName()); //anonymous user support
	}

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody final User user) {
        userFacade.createNewAccount(user);
    }

}
