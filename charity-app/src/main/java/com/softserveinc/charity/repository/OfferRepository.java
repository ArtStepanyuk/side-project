package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "offers", path = "offers")
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
