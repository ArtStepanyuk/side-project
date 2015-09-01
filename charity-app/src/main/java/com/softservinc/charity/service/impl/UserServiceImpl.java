package com.softservinc.charity.service.impl;

import com.softservinc.charity.dao.UserDao;
import com.softservinc.charity.model.User;
import com.softservinc.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public void save(User user) {
        dao.save(user);
    }
    public User getByEmail(String email){
        return dao.getByEmail(email);
    }

}
