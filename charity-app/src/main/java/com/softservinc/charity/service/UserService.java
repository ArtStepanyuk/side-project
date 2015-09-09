package com.softservinc.charity.service;

import com.softservinc.charity.exceptions.UserAlreadyExistException;
import com.softservinc.charity.model.User;

public interface UserService {
    void save(User user) throws UserAlreadyExistException;
    public User getByEmail(String email);
    public void refresh(User user);
}
