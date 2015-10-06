package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.need.NeedDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface NeedSearchRepository extends ElasticsearchRepository<NeedDetails, Integer> {
    List<NeedDetails> findByName(String name);
}