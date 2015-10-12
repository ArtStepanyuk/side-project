package com.softserveinc.charity.elasticsearch;

import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.model.offer.OfferDetails;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElasticSearchService {

    @Autowired
    NeedSearchRepository needSearchRepository;

    @Autowired
    OfferSearchRepository offerSearchRepository;

    public FacetedPage<NeedDetails> findNeeds(Boolean wildcard, String query, String region, String city, String category) {
        SearchQuery searchQuery = createSearchQuery(wildcard, query, region, city, category);
        return needSearchRepository.search(searchQuery);
    }

    public FacetedPage<OfferDetails> findOffers(Boolean wildcard, String query, String region, String city, String category) {
        SearchQuery searchQuery = createSearchQuery(wildcard, query, region, city, category);
        return offerSearchRepository.search(searchQuery);
    }

    private SearchQuery createSearchQuery(Boolean wildcard, String query, String region, String city, String category) {
        SearchQueryBuilder searchQueryBuilder;

        if (wildcard.equals(Boolean.TRUE)){
            searchQueryBuilder = new SearchQueryBuilder(query);
        } else {
            searchQueryBuilder = new SearchQueryBuilder(query, region, city, category);
        }

        return new NativeSearchQueryBuilder()
                .withQuery(searchQueryBuilder.build())
                .withPageable(new PageRequest(0, 10))
                .build();
    }
}