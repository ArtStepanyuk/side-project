package com.softserveinc.charity.controller;

import com.softserveinc.charity.exceptions.UserAlreadyExistException;
import com.softserveinc.charity.repository.UserRepository;
import com.softserveinc.charity.util.Constants;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/users")
@ExposesResourceFor(User.class)
public class UserController extends AbstractController implements
        ResourceProcessor<RepositoryLinksResource> {

    private static final Logger LOG = LoggerFactory.getLogger("com.softservinc.charity");

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody final User user) {
        if (userRepo.findByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistException(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.saveAndFlush(user);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource
                .add(ControllerLinkBuilder.linkTo(UserController.class)
                        .withRel("/api/users/current"));
        resource
                .add(ControllerLinkBuilder.linkTo(UserController.class)
                        .withRel("/api/users/register"));
        return resource;
    }
}
