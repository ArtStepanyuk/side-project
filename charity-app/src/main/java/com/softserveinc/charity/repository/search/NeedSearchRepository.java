package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Need;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface NeedSearchRepository
        extends ElasticsearchRepository<Need, String>
{
    List<Need> findByName(String name);
    List<Need> findByName(String name, Pageable pageable);
    //List<Need> findByNameAndId(String name, String id);
}
