package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.projections.UserInLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "users", path = "users", excerptProjection = UserInLine.class)
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(@Param("email") String email);

    // TODO fix security
    @Override
    User save(User user);
}
