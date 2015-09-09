package com.softservinc.charity.dao;

import com.softservinc.charity.model.UserRole;

public interface UserRoleDao {

    UserRole getByRole(String role);
}
