package com.softservinc.charity.serviceImpl;

import com.softservinc.charity.dao.NeedDao;
import com.softservinc.charity.model.Need;
import com.softservinc.charity.model.User;
import com.softservinc.charity.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("needService")
@Transactional
public class NeedServiceImpl implements NeedService{

    @Autowired
    private NeedDao dao;

    public void save(Need need) {
        dao.save(need);
    }
    public Need getById(int id){
        return dao.getById(id);
    }
    public List<Need> getNeedsByUser(User user){
        return dao.getNeedsByUser(user);
    }
    public List<Need> getAll(){
        return dao.getAll();
    }
}
