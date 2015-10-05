package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.NeedResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "needs_responses", path = "needResponses")
public interface NeedResponseRepository extends JpaRepository<NeedResponse, Integer> {}
