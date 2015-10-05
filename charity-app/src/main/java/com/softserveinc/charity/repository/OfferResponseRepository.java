package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.OfferResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "offer_responses", path = "offerResponses")
public interface OfferResponseRepository extends JpaRepository<OfferResponse, Integer> {
}
