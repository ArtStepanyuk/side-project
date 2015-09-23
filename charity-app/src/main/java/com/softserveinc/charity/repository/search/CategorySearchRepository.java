package com.softserveinc.charity.repository.search;

import com.softserveinc.charity.model.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CategorySearchRepository extends ElasticsearchRepository<Category, Integer> {
}
