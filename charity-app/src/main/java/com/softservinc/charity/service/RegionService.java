package com.softservinc.charity.service;

import com.softservinc.charity.model.City;
import com.softservinc.charity.model.Region;
import java.util.List;

public interface RegionService {
    List<Region> getAll();
    List<City> getCitiesByRegionId(int regionId);
}
