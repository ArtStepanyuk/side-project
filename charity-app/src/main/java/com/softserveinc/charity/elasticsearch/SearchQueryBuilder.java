package com.softserveinc.charity.elasticsearch;

import com.google.common.collect.ImmutableMap;
import com.softserveinc.charity.exceptions.CategoryNotPresentException;
import com.softserveinc.charity.model.Category;
import com.softserveinc.charity.repository.jpa.CategoryRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
    private static final Logger LOG = Logger.getLogger(SearchQueryBuilder.class);

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
    private static final String ROOT = "root";

    private static final Map<Integer, String> categoryNamesMap = ImmutableMap.of(
            0, "category.name",
            1, "category.parent.name",
            2, "category.parent.parent.name",
            3, "category.parent.parent.parent.name"
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
     * If input category is empty - parents is set to 'root'.
     *
     * nestedCategoryNameStartIdx is distance between current category and length of branch.
     * By default is 1 which means search only in parent categories.
     *
     * For example:
     *
     * 1.
     * Input format for categories are "clothes,for men,outerwear".
     * 'outerwear' has no children.
     *
     * maxDepth = 3, depth = 3 -> nestedCategoryNameStartIdx = 0
     *
     * Builder will create query using categoryNamesMap
     * where 'outerwear' is category.name, 'for men' is category.parent.name, and etc.
     *
     * 2.
     * Input format for categories are "clothes,for men".
     * 'for men' has children
     *
     * maxDepth = 3, depth = 2 -> nestedCategoryNameStartIdx = 1
     *
     * Builder will create query using categoryNamesMap
     * where 'for men' is category.parent.name, and etc.
     * category.name is not used.
     *
     *
     *
     * @param builder - boolQueryBuilder
     */
    private void attachCategory(BoolQueryBuilder builder){
        BoolQueryBuilder boolQuery = boolQuery();
        Integer nestedCategoryNameStartIdx = 1;

        if (category != null && StringUtils.isNotBlank(category)){
                String categoryStr = ROOT + "," + category;
                String [] categories = categoryStr.split(",");
                Category categoryEntity = categoryRepository.findByNameAndParent(
                        categories[categories.length - 1], categories[categories.length - 2]);
                if (categoryEntity != null) {
                    nestedCategoryNameStartIdx = categoryEntity.getMaxDepth() - categoryEntity.getDepth();

                    for (int i = nestedCategoryNameStartIdx, k =  categories.length - 1;i < categories.length; i++, k--){
                        boolQuery.must(nestedQuery(CATEGORY, matchPhraseQuery(categoryNamesMap.get(i), categories[k])));
                    }
                } else {
                    LOG.error(String.format("Couldn't find category for string { %s }", category));
                    throw new CategoryNotPresentException();
                }
        }
        else {
            for (int i = nestedCategoryNameStartIdx;i < categoryNamesMap.size();i++){
                boolQuery.should(nestedQuery(CATEGORY, termQuery(categoryNamesMap.get(i), ROOT)));
            }
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
