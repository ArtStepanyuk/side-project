package com.softserveinc.charity.service;

import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.model.offer.OfferDetails;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
@Transactional
public class SearchService {

    @Autowired
    NeedSearchRepository needSearchRepository;

    @Autowired
    OfferSearchRepository offerSearchRepository;

    public FacetedPage<NeedDetails> findNeeds(String userInput){
        BoolQueryBuilder builder = boolQuery();
        builder
                .should(matchQuery("name", userInput))
                .should(matchQuery("description", userInput))
                .should(matchQuery("address", userInput))
                .should(nestedQuery("category", termQuery("category.name", userInput)))
                .should(nestedQuery("city", termQuery("city.name", userInput)))
                .should(nestedQuery("city.region", queryString(userInput).field("city.region.name")));

                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                        .withQuery(builder)
                        .withPageable(new PageRequest(0, 10))
                        .build();

        return needSearchRepository.search(searchQuery);
    }

    public FacetedPage<OfferDetails> findOffers(String userInput){
        BoolQueryBuilder builder = boolQuery();
        builder
                .should(matchQuery("name", userInput))
                .should(matchQuery("description", userInput))
                .should(matchQuery("address", userInput))
                .should(nestedQuery("category", termQuery("category.name", userInput)))
                .should(nestedQuery("city", termQuery("city.name", userInput)))
                .should(nestedQuery("city.region", queryString(userInput).field("city.region.name")));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(new PageRequest(0, 10))
                .build();

        return offerSearchRepository.search(searchQuery);
    }
}
