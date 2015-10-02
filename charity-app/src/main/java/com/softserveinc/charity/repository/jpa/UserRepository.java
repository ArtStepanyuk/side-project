package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(@Param("email") String email);

    @Override
    @RestResource(exported = false)
    User save(User user);
}
