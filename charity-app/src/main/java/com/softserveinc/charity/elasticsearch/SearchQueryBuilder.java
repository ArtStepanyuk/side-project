package com.softserveinc.charity.elasticsearch;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class SearchQueryBuilder {

    private String city;
    private String region;
    private String category;
    private String query;

    private SearchQueryBuilder(){
        //
    }

    public SearchQueryBuilder(String query) {
        this.query = query;
    }

    public SearchQueryBuilder(String query, String region, String city, String category) {
        this.city = city;
        this.region = region;
        this.category = category;
        this.query = query;
    }

    public QueryBuilder build(){
        BoolQueryBuilder builder = boolQuery();
        builder
                .should(matchQuery("name", query))
                .should(matchQuery("description", query))
                .should(matchQuery("address", query))
                .should(nestedQuery("userCreated", termQuery("userCreated.name", query)));

        if (category != null && StringUtils.isNotEmpty(category)){
            builder.should(nestedQuery("category", termQuery("category.name", category)));
        }
        if (city != null && StringUtils.isNotEmpty(city)) {
            builder.should(nestedQuery("city", termQuery("city.name", city)));
        }
        if (region != null && StringUtils.isNotEmpty(region)) {
            builder.should(nestedQuery("city.region", queryString(region).field("city.region.name")));
        }
        return builder;
    }
}
