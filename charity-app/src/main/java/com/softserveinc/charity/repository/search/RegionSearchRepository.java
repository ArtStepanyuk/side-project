package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RegionSearchRepository extends ElasticsearchRepository<Region, Integer>{
    List<Region> findByName(String name);
    List<Region> findByName(String name, Pageable pageable);
}
