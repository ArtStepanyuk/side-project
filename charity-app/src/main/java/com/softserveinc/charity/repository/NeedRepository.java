package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Need;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository(value = NeedRepository.NAME)
@RepositoryRestResource(collectionResourceRel = "needs", path = "needs")
public interface NeedRepository extends JpaRepository<Need, Integer> {
    String NAME = "needRepository";

    @EntityGraph(value = "Need.detail")
    Need findOne(Integer id);
}
