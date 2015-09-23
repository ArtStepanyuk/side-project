package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Need;
import org.elasticsearch.bootstrap.Elasticsearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;
import java.util.List;

public interface NeedSearchRepository
        extends ElasticsearchRepository<Need, String>
{
    List<Need> findByName(String name);
    List<Need> findByName(String name, Pageable pageable);
    //List<Need> findByNameAndId(String name, String id);
}
