package com.softserveinc.charity.service.impl;

import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserAuthentication;
import com.softserveinc.charity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Override
    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }
        //anonymous user
        return new User(authentication.getName());
    }
}
