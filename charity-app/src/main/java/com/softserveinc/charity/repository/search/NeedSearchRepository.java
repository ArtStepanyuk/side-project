package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Need;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface NeedSearchRepository extends ElasticsearchRepository<Need, String>
{
    List<Need> findByName(String name);
}
