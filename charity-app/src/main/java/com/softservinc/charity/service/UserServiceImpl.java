package com.softservinc.charity.service;

import com.softservinc.charity.dao.UserDao;
import com.softservinc.charity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    public void save(User user) {
        dao.save(user);
    }
}
