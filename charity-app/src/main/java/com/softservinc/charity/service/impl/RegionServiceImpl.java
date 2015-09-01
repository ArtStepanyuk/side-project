package com.softservinc.charity.service.impl;

import com.softservinc.charity.dao.CityDao;
import com.softservinc.charity.dao.RegionDao;
import com.softservinc.charity.model.City;
import com.softservinc.charity.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("regionService")
public class RegionServiceImpl implements RegionDao{

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private CityDao cityDao;

    public List<Region> getAll() {
        return regionDao.getAll();
    }

    public List<City> getCitiesByRegionId(int regionId){
        return cityDao.getCitiesByRegionId(regionId);
    }
}
