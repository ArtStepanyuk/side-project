package com.softservinc.charity.dao.impl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.RegionDao;
import com.softservinc.charity.model.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("regionDao")
public class RegionDaoImpl extends AbstractDao implements RegionDao {
    public List<Region> getAll(){
        return getSession().createCriteria(Region.class).list();
    }
}
