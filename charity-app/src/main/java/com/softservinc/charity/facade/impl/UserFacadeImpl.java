package com.softservinc.charity.facade.impl;

import com.google.common.collect.Sets;
import com.softservinc.charity.facade.UserFacade;
import com.softservinc.charity.model.User;
import com.softservinc.charity.model.UserRole;
import com.softservinc.charity.service.UserRoleService;
import com.softservinc.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userFacade")
public class UserFacadeImpl implements UserFacade{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createNewAccount(User user) {
        UserRole userRole = userRoleService.getByRole("USER");
        user.setRoles(Sets.newHashSet(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
