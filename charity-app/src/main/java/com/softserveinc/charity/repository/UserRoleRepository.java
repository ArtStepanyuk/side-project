package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userroles", path = "userroles")
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {
}
