package com.softserveinc.charity.elasticsearch;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class SearchQueryBuilder {

    private static final String WILDCARD_POSTFIX = "*";

    private String city;
    private String region;
    private String category;
    private String query;
    private boolean wildcard;

    private SearchQueryBuilder(){
        //
    }

    /**
     * Wirecard search.
     *
     * @param query - user input.
     */
    public SearchQueryBuilder(String query) {
        this.wildcard = true;
        this.query = query;
    }

    /**
     * Search with mandatory parameters.
     *
     * @param query - user input
     * @param region - mandatory parameter
     * @param city - non mandatory parameter
     * @param category - category
     */
    public SearchQueryBuilder(String query, String region, String city, String category) {
        this.wildcard = false;
        this.city = city;
        this.region = region;
        this.category = category;
        this.query = query;
    }

    /**
     *
     * @return query builder.
     */
    public QueryBuilder build(){
        return wildcard ? buildWildcardQuery() : buildQueryWithParameters();
    }

    /**
     * TODO
     */
    private QueryBuilder buildWildcardQuery(){
        BoolQueryBuilder builder = boolQuery();

        String wildcardQuery = query;

        if (!wildcardQuery.endsWith(WILDCARD_POSTFIX)){
            wildcardQuery += WILDCARD_POSTFIX;
        }

        builder.should(
                queryStringQuery(wildcardQuery)
                        .analyzeWildcard(true)
                        .field("name", 2.0f)
                        .field("description", 1.0f));

        builder
                .should(nestedQuery("userCreated",
                        queryStringQuery(wildcardQuery)
                                .field("userCreated.name", 3.0f)));

        return builder;
    }

    // TODO create contract - there must be always region, if no category - select root category
    private QueryBuilder buildQueryWithParameters(){
        BoolQueryBuilder builder = boolQuery();

        if (query != null && StringUtils.isNotEmpty(query)){
            builder.should(multiMatchQuery(query, "name", "description", "address"))
                .should(nestedQuery("userCreated", termQuery("userCreated.name", query)));
        }

        if (category != null && StringUtils.isNotEmpty(category)){
            builder.must(nestedQuery("category", termQuery("category.name", category)));
        }
        if (city != null && StringUtils.isNotEmpty(city)) {
            builder.must(nestedQuery("city", termQuery("city.name", city)));
        }
        if (region != null && StringUtils.isNotEmpty(region)) {
            builder.must(nestedQuery("city.region", queryString(region).field("city.region.name")));
        }
        return builder;
    }
}
