package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CitySearchRepository
        extends ElasticsearchRepository<City, Integer>
{
        List<City> findByName(String name);
}
