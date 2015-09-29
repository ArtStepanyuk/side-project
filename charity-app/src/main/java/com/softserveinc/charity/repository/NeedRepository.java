package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "needs", path = "needs")
public interface NeedRepository extends JpaRepository<Need, Integer> {}
