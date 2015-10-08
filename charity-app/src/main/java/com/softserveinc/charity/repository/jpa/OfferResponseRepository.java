package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.OfferResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "offer_response", path = "offerResponses")
public interface OfferResponseRepository extends JpaRepository<OfferResponse, Integer> {
}
