package com.softserveinc.charity.elasticsearch;

import com.softserveinc.charity.model.need.NeedDetails;
import com.softserveinc.charity.model.offer.OfferDetails;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.repository.search.OfferSearchRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Search service that encapsulates creation of search query via builder and performs search.
 */
@Service
@Transactional
public class ElasticSearchService {

    @Autowired
    NeedSearchRepository needSearchRepository;

    @Autowired
    OfferSearchRepository offerSearchRepository;

    /**
     * Create search query for needs based on input parameters.
     *
     * @param wildcard - flag that defines type of search
     * @param query - query string
     * @param region - name of region
     * @param city - name of city
     * @param category - name of category
     * @param pageable - Pageable(page, size, sort)
     * @return page with need documents
     */
    public FacetedPage<NeedDetails> findNeeds(Boolean wildcard, String query, String region, String city, String category, Pageable pageable) {
        SearchQuery searchQuery = createSearchQuery(wildcard, query, region, city, category, pageable);
        return needSearchRepository.search(searchQuery);
    }

    /**
     * Create search query for offers based on input parameters.
     *
     * @param wildcard - flag that defines type of search
     * @param query - query string
     * @param region - name of region
     * @param city - name of city
     * @param category - name of category
     * @param pageable - Pageable(page, size, sort)
     * @return page with offer documents
     *
     * TODO refactor needDetails, offerDetails in way to get rid of duplicated logic for elasticsearch documents.
     */
    public FacetedPage<OfferDetails> findOffers(Boolean wildcard, String query, String region, String city, String category, Pageable pageable) {
        SearchQuery searchQuery = createSearchQuery(wildcard, query, region, city, category, pageable);
        return offerSearchRepository.search(searchQuery);
    }

    private SearchQuery createSearchQuery(Boolean wildcard, String query, String region, String city, String category, Pageable pageable) {
        SearchQueryBuilder searchQueryBuilder;

        boolean isWildcard = wildcard.equals(Boolean.TRUE);
        searchQueryBuilder = new SearchQueryBuilder(isWildcard, query, region, city, category);

        QueryBuilder queryBuilder = searchQueryBuilder.build();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();

        return searchQuery;
    }
}
