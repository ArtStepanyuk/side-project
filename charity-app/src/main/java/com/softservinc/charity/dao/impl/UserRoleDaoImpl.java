package com.softservinc.charity.dao.impl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.UserRoleDao;
import com.softservinc.charity.model.UserRole;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRoleDao")
@Transactional
public class UserRoleDaoImpl extends AbstractDao implements UserRoleDao {
    @Override
    public UserRole getByRole(String role) {
        Query query = getSession().getNamedQuery("findUserRoleByRole")
                .setString("role", role);
        return (UserRole)query.uniqueResult();
    }
}
