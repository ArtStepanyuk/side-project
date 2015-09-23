package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Region;
import org.elasticsearch.bootstrap.Elasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")
public interface RegionRepository extends JpaRepository<Region, String> {
//public interface RegionRepository extends ElasticsearchRepository<Region, String> {
}