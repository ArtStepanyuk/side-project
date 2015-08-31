package com.softservinc.charity.dao;

import com.softservinc.charity.model.City;

import java.util.List;

public interface CityDao {
    List<City> getCitiesByRegionId(int regionId);
}
