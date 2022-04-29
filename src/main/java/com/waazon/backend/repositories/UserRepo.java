package com.waazon.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.User;

import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String userName);

    User findUserByEmail(String email);
}
