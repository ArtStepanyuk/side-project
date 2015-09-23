package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends JpaRepository<City, String> {
//public interface CityRepository extends ElasticsearchRepository<City, String> {
    List<City> findByRegionId(@Param("region_id") Integer regionId);
    List<City> findAll();
}
