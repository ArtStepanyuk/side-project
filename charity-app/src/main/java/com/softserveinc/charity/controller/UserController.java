package com.softserveinc.charity.controller;

import com.softserveinc.charity.util.Constants;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/users")
@ExposesResourceFor(User.class)
public class UserController extends AbstractController implements
        ResourceProcessor<RepositoryLinksResource> {

    private static final Logger LOG = LoggerFactory.getLogger("com.softservinc.charity");

    // TODO could be implemented with projections
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public User getCurrent(final HttpServletRequest request) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			User user =  ((UserAuthentication) authentication).getDetails();
			// TODO: Added token to user for UI integration. Remove after.
			user.setToken(request.getHeader(Constants.AUTH_HEADER_NAME));
			return user;
		}
        //anonymous user
		return new User(authentication.getName());
	}

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource
                .add(ControllerLinkBuilder.linkTo(UserController.class)
                        .withRel("/api/users/current"));
        return resource;
    }
}
