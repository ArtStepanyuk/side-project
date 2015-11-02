package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    @RestResource(path = "root", rel="root")
    @Query("select c from Category c where c.parent=NULL")
    Category getRoot();

    @Query("select c from Category c where c.name=?1 and c.parent.name=?2")
    Category findByNameAndParent(String name, String parentName);
}
