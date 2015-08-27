package com.softservinc.charity.dao;

import com.softservinc.charity.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao{
    public void save(User user) {
        persist(user);
    }
}
