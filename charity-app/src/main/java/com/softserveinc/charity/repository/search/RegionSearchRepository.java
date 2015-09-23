package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Region;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RegionSearchRepository extends ElasticsearchRepository<Region, String>{
}
