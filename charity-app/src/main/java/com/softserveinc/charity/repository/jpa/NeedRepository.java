package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.need.NeedInLineProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository(value = NeedRepository.NAME)
@RepositoryRestResource(collectionResourceRel = "needs", path = "needs", excerptProjection = NeedInLineProjection.class)
public interface NeedRepository extends JpaRepository<Need, Integer> {
    String NAME = "needRepository";

    @EntityGraph(value = "Need.detail")
    Need findOne(Integer id);
}
