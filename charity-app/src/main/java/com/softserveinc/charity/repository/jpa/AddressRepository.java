package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends PagingAndSortingRepository<Address, Integer>
{
}
