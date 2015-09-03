package com.softservinc.charity.service.impl;

import com.softservinc.charity.dao.UserRoleDao;
import com.softservinc.charity.model.UserRole;
import com.softservinc.charity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRole getByRole(String role) {
        return userRoleDao.getByRole(role);
    }
}
