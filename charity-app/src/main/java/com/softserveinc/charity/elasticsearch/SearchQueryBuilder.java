package com.softserveinc.charity.elasticsearch;

import com.google.common.collect.ImmutableMap;
import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.repository.jpa.CategoryRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Builder that encapsulates creation of plain and wildcard search.
 */
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

    private static final Map<Integer, String> map = ImmutableMap.of(
            0, CATEGORY_NAME,
            1, CATEGORY_PARENT_NAME,
            2, CATEGORY_GRANDPARENT_NAME,
            3, CATEGORY_GRANDGRANDPARENT_NAME
    );

    private String city;
    private String region;
    private String category;
    private String query;
    private boolean wildcard;

    private CategoryRepository categoryRepository;

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
     * Main creation method.
     *
     * @return query builder depending on wildcard flag.
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
        attachCategory(builder);
        attachCity(builder);
        attachRegion(builder);
    }


    /**
     * Attach category to boolQuery.
     * Input format for categories is "category1,category1.1,category1.1.1"
     *
     *
     * @param builder - boolQueryBuilder
     */
    private void attachCategory(BoolQueryBuilder builder){
        BoolQueryBuilder boolQuery = boolQuery();
        if (category != null && StringUtils.isNotEmpty(category)){
                String categoryStr = ROOT + "," + category;
                String [] categories = categoryStr.split(",");
                Category categoryEntity =
                                categoryRepository.findByNameAndParent(
                                        categories[categories.length - 1], categories[categories.length - 2]);
                Integer level = categoryEntity.getMaxLvl() - categoryEntity.getLvl();

                for (int i = level, k =  categories.length - 1;i < categories.length; i++, k--){
                    boolQuery.must(nestedQuery(CATEGORY, termQuery(map.get(i), categories[k])));
                }
        }
        else {
            boolQuery
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_PARENT_NAME, ROOT)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_GRANDPARENT_NAME, ROOT)))
                    .should(nestedQuery(CATEGORY, termQuery(CATEGORY_GRANDGRANDPARENT_NAME, ROOT)));
        }
        builder.must(boolQuery);
    }

    /**
     * Attach city to boolQuery
     *
     * @param builder - boolQueryBuilder
     */
    private void attachCity(BoolQueryBuilder builder){
        if (city != null && StringUtils.isNotEmpty(city)) {
            builder.must(nestedQuery(CITY, termQuery(CITY_NAME, city)));
        }
    }

    /**
     * Attach region to boolQuery
     *
     * @param builder - boolQueryBuilder
     */
    private void attachRegion(BoolQueryBuilder builder){
        if (region != null && StringUtils.isNotEmpty(region)) {
            builder.must(nestedQuery(CITY_REGION, queryString(region).field(CITY_REGION_NAME)));
        }
    }

    /**
     * Setter for categoryRepository
     *
     * @param categoryRepository - repository for categories
     */
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

}
