package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.City;

import com.softserveinc.charity.model.projections.InlineCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities", excerptProjection = InlineCity.class)
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByRegionId(@Param("region_id") Integer regionId);
    List<City> findAll();
}
