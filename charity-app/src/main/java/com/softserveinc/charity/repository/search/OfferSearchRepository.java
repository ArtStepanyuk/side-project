package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Offer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OfferSearchRepository extends ElasticsearchRepository<Offer, String>
{
    List<Offer> findByName(String name);
}
