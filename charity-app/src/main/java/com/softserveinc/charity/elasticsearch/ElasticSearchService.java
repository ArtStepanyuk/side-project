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

    public FacetedPage findNeeds(Boolean wirecard, String query, String region, String city, String category) {
        if (wirecard == null || wirecard.equals(Boolean.FALSE)){
            return findNeeds(query, region, city, category);
        }
        return findNeeds(query);
    }

    private FacetedPage<NeedDetails> findNeeds(String query){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder(query);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(searchQueryBuilder.build())
                .withPageable(new PageRequest(0, 10))
                .build();

        return needSearchRepository.search(searchQuery);
    }

    private FacetedPage<NeedDetails> findNeeds(String query, String region, String city, String category){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder(query, region, city, category);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(searchQueryBuilder.build())
                .withPageable(new PageRequest(0, 10))
                .build();

        return needSearchRepository.search(searchQuery);
    }

    public FacetedPage findOffers(Boolean wirecard, String query, String region, String city, String category) {
        if (wirecard == null || wirecard.equals(Boolean.FALSE)){
            return findOffers(query, region, city, category);
        }
        return findOffers(query);
    }

    private FacetedPage<OfferDetails> findOffers(String query){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder(query);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(searchQueryBuilder.build())
                .withPageable(new PageRequest(0, 10))
                .build();

        return offerSearchRepository.search(searchQuery);
    }

    private FacetedPage<OfferDetails> findOffers(String query, String region, String city, String category){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder(query, region, city, category);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(searchQueryBuilder.build())
                .withPageable(new PageRequest(0, 10))
                .build();

        return offerSearchRepository.search(searchQuery);
    }
}
