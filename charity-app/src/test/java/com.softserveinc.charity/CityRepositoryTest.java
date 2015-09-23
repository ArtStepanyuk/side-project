package com.softserveinc.charity;

import com.softserveinc.charity.model.City;
import com.softserveinc.charity.model.Region;
import com.softserveinc.charity.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CityRepositoryTest extends AbstractWebIntegrationTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void test_get_all_cities(){
        List<City> cities = cityRepository.findAll();
        Assert.assertNotNull(cities);
        Assert.assertTrue(cities.size() > 10);
    }

    @Test
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void test_get_all_cities_by_region_id(){
        List<City> cities = cityRepository.findByRegionId(1);
        Assert.assertNotNull(cities);
        Region region = cities.get(0).getRegion();
        Assert.assertNotNull(region);
        Assert.assertNotNull(region.getName());
    }
}
