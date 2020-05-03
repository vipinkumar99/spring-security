package org.oauth.api.repository;

import java.util.Optional;

import org.oauth.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String name);
}
