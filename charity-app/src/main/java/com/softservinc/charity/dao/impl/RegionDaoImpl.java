package com.softservinc.charity.dao.impl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.RegionDao;
import com.softservinc.charity.model.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("regionDao")
@Transactional
public class RegionDaoImpl extends AbstractDao implements RegionDao{
    public List<Region> getAll(){
        return getSession().createCriteria(Region.class).list();
    }
}
