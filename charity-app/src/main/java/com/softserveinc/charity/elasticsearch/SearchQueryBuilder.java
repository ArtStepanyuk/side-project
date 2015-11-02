package com.softserveinc.charity.elasticsearch;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class SearchQueryBuilder {

    private static final String WILDCARD_POSTFIX = "*";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ADDRESS = "address";
    private static final String CATEGORY = "category";
    private static final String CITY = "city";
    private static final String CITY_REGION = "city.region";
    private static final String CITY_REGION_NAME = "city.region.name";
    private static final String CITY_NAME = "city.name";
    private static final String USER_CREATED = "userCreated";
    private static final String USER_CREATED_NAME = "userCreated.name";
    private static final String CATEGORY_NAME = "category.name";
    private static final String CATEGORY_PARENT_NAME = "category.parent.name";
    private static final String CATEGORY_GRANDPARENT_NAME = "category.parent.parent.name";
    private static final String CATEGORY_GRANDGRANDPARENT_NAME = "category.parent.parent.parent.name";
    private static final String ROOT = "root";

    private String city;
    private String region;
    private String category;
    private String query;
    private boolean wildcard;

    private SearchQueryBuilder(){
        //
    }

    /**
     * Search with mandatory parameters.
     *
     * @param wildcard - wildcard search
     * @param query - user input
     * @param region - mandatory parameter
     * @param city - non mandatory parameter
     * @param category - category
     */
    public SearchQueryBuilder(boolean wildcard, String query, String region, String city, String category) {
        this.wildcard = wildcard;
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

        String wildcardQuery = query == null ? "" : query;

        if (!wildcardQuery.endsWith(WILDCARD_POSTFIX)){
            wildcardQuery += WILDCARD_POSTFIX;
        }

        attachCommonQueryParameters(builder);

        builder
                .must(
                        boolQuery()
                                .should(queryStringQuery(wildcardQuery)
                                        .analyzeWildcard(true).field(NAME, 2.0f).field(DESCRIPTION, 1.0f))
                                .should(nestedQuery(USER_CREATED,
                                        queryStringQuery(wildcardQuery).field(USER_CREATED_NAME, 3.0f))));

        return builder;
    }

    private QueryBuilder buildQueryWithParameters(){
        BoolQueryBuilder builder = boolQuery();

        attachCommonQueryParameters(builder);

        if (query != null && StringUtils.isNotEmpty(query)){
            builder
                    .must(boolQuery()
                            .should(multiMatchQuery(query, NAME, DESCRIPTION, ADDRESS))
                            .should(nestedQuery(USER_CREATED, termQuery(USER_CREATED_NAME, query))));
        }

        return builder;
    }

    /**
     *
     * @param builder - common quires for both methods.
     *
     * If category present, nested query on current, parent and grandparent categories.
     * If not - search with root category through all items.
     * City and region is not mandatory.
     */
    private void attachCommonQueryParameters(BoolQueryBuilder builder) {
        if (category != null && StringUtils.isNotEmpty(category)){
            builder.must(boolQuery()
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_NAME, category)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_PARENT_NAME, category)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_GRANDPARENT_NAME, category))));
        }
        else {
            builder.must(boolQuery()
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_PARENT_NAME, ROOT)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_GRANDPARENT_NAME, ROOT)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_GRANDGRANDPARENT_NAME, ROOT))));
        }
        if (city != null && StringUtils.isNotEmpty(city)) {
            builder.must(nestedQuery(CITY, termQuery(CITY_NAME, city)));
        }
        if (region != null && StringUtils.isNotEmpty(region)) {
            builder.must(nestedQuery(CITY_REGION, queryString(region).field(CITY_REGION_NAME)));
        }
    }

}
