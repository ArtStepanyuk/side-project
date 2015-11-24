package com.softserveinc.charity.repository.handler;

import com.google.common.collect.Sets;
import com.softserveinc.charity.exceptions.AddressNotPresentException;
import com.softserveinc.charity.exceptions.UserAlreadyExistException;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserRole;
import com.softserveinc.charity.repository.jpa.UserRepository;
import com.softserveinc.charity.repository.jpa.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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

        Set<UserRole> userRoles = Sets.newHashSet(userRoleRepository.findByRole("USER"));
        user.setRoles(userRoles);
    }
}
