package com.softserveinc.charity.repository.jpa;

import com.softserveinc.charity.model.User;
import com.softserveinc.charity.model.UserInLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository(value = UserRepository.NAME)
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {
    String NAME = "userRepository";

    User findByEmail(@Param("email") String email);

    // TODO fix security
    @Override
    User save(User user);
}
