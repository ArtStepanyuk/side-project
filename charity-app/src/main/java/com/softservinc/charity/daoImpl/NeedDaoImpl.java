package com.softservinc.charity.daoImpl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.NeedDao;
import com.softservinc.charity.model.Need;
import com.softservinc.charity.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("need")
@Transactional
public class NeedDaoImpl extends AbstractDao implements NeedDao {
    public void save(Need need) {
        persist(need);
    }
    public Need getById(int id){
        return getSession().get(Need.class, id);
    }
    public List<Need> getNeedsByUser(User user){
        Query query = getSession().getNamedQuery("findNeedsByUser")
                .setString("id", user.getId().toString());
        return query.list();
    }
    public List<Need> getAll(){
        return getSession().createCriteria(Need.class).list();
    }
}
