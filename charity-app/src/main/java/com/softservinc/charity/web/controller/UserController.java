package com.softservinc.charity.web.controller;

import com.softservinc.charity.entity.security.User;
import com.softservinc.charity.entity.security.UserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController {

	//@Autowired
	//UserRepository userRepository;

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			return ((UserAuthentication) authentication).getDetails();
		}
		return new User(authentication.getName()); //anonymous user support
	}
}
