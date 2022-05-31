package com.danplay.server.user.domain.repository;

import com.danplay.server.user.domain.entity.PreferSport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferSportRepository extends JpaRepository<PreferSport, Long> {

}
