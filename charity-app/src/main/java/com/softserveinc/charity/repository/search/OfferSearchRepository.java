package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.offer.OfferDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface OfferSearchRepository extends ElasticsearchRepository<OfferDetails, Integer>
{
    List<OfferDetails> findByName(String name);
}
