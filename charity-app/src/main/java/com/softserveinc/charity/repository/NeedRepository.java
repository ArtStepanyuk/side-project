package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Need;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface NeedRepository extends ElasticsearchRepository<Need, String> {
    List<Need> findByName(String name);
    List<Need> findByName(String name, Pageable pageable);
    List<Need> findByNameAndId(String name, String id);
}
