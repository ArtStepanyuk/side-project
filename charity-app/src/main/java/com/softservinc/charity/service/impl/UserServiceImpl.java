package com.softservinc.charity.service.impl;

import com.softservinc.charity.dao.UserDao;
import com.softservinc.charity.exceptions.UserAlreadyExistException;
import com.softservinc.charity.model.User;
import com.softservinc.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public void save(User user) throws UserAlreadyExistException{
        if (isEmailExist(user.getEmail()))
        {
            throw new UserAlreadyExistException(String.format("User with email %s already exists.", user.getEmail()));
        }
        dao.save(user);
    }

    public User getByEmail(String email){
        return dao.getByEmail(email);
    }

    @Override
    public void refresh(User user) {
        dao.refresh(user);
    }

    private boolean isEmailExist(final String email) {
        final User user = dao.getByEmail(email);
        return user != null;
    }
}
