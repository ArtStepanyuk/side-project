package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.NeedResponse;
import com.softserveinc.charity.model.need.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "need_response", path = "needResponses")
public interface NeedResponseRepository extends JpaRepository<NeedResponse, Integer> {
    List<NeedResponse> findByNeed(@Param("need") Need need);
}
