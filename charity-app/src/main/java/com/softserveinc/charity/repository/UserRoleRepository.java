package com.softserveinc.charity.repository;

import com.softserveinc.charity.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "userroles", path = "userroles")
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
