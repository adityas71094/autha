package com.adiSuper.autha.repository;

import com.adiSuper.autha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findOneByEmailId(String emailID);

    public Optional<User> findOneByUsername(String username);
}
