package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.offer.Offer;
import com.softserveinc.charity.model.offer.OfferInLineProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository(value = OfferRepository.NAME)
@RepositoryRestResource(collectionResourceRel = "offers", path = "offers", excerptProjection = OfferInLineProjection.class)
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    String NAME = "offerRepository";

    @EntityGraph(value = "Offer.detail")
    Offer findOne(Integer id);
}
