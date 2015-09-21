package com.softserveinc.charity;

import com.softserveinc.charity.model.City;
import com.softserveinc.charity.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void test_get_all_cities_by_region_id(){
        List<City> city = cityRepository.findByRegionId(1);
        Assert.assertNotNull(city);
    }
}
