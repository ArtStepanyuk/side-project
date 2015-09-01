package com.softservinc.charity.dao.impl;

import com.softservinc.charity.dao.AbstractDao;
import com.softservinc.charity.dao.CityDao;
import com.softservinc.charity.model.City;
import com.softservinc.charity.model.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cityDao")
@Transactional
public class CityDaoImpl extends AbstractDao implements CityDao{
    public List<City> getCitiesByRegionId(int regionId){
        Region region = getSession().get(Region.class, regionId);
        return region.getCities();
    }
}