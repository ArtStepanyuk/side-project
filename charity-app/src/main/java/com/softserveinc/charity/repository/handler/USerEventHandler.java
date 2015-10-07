package com.softserveinc.charity.repository.handler;

import com.softserveinc.charity.exceptions.AddressNotPresentException;
import com.softserveinc.charity.exceptions.UserAlreadyExistException;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        if (user.getAddress() == null){
            throw new AddressNotPresentException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
