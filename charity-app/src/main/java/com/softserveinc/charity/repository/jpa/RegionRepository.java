package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region getOne(@Param("id") Integer id);
    List<Region> findAll();
}