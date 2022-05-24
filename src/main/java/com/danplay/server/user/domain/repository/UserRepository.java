package com.danplay.server.user.domain.repository;

import com.danplay.server.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByMail(String mail);

    Optional<User> getUserByMail(String mail);
}
