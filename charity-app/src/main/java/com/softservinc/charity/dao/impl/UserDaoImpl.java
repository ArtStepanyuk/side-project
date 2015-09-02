package com.softservinc.charity.dao.impl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.UserDao;
import com.softservinc.charity.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao implements UserDao {
    public void save(User user) {
        persist(user);
    }
    public User getByEmail(String email){
        Query query = getSession().getNamedQuery("findUserByEmail")
                .setString("email", email);
        return (User)query.uniqueResult();
    }
    public void refresh(User user)
    {
        getSession().refresh(user);
    }
}
