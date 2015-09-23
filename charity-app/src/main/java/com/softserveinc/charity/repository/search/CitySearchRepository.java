package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CitySearchRepository
        extends ElasticsearchRepository<City, String>
{
}
