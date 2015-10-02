package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Offer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface OfferSearchRepository extends ElasticsearchRepository<Offer, Integer>
{
    List<Offer> findByName(String name);
}
