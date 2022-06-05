package com.danplay.server.usermatch.domain.repository;

import com.danplay.server.usermatch.domain.entity.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {

}
