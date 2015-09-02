package com.softservinc.charity.dao;

import com.softservinc.charity.model.User;

public interface UserDao {
    void save(User user);
    User getByEmail(String email);
    void refresh(User user);
}
