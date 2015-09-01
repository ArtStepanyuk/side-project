package com.softservinc.charity.service;

import com.softservinc.charity.model.Need;
import com.softservinc.charity.model.User;

import java.util.List;

public interface NeedService {
    void save(Need need);
    Need getById(int id);
    List<Need> getNeedsByUser(User user);
    List<Need> getAll();
}
