package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.Offer;
import com.softserveinc.charity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "offers", path = "offers")
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
