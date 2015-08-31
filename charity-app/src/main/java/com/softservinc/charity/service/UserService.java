package com.softservinc.charity.service;

import com.softservinc.charity.model.User;

public interface UserService {
    void save(User user);
    public User getByEmail(String email);
}
