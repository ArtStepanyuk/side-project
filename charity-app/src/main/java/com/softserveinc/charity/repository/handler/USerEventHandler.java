package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.exceptions.UserAlreadyExistException;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.jpa.UserRepository;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @HandleBeforeCreate
    public void handleBeforeCreate(User user){
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistException(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
