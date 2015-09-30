package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Need;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface NeedSearchRepository extends ElasticsearchRepository<Need, Integer>
{
    //List<Need> findByName(String name);
}
