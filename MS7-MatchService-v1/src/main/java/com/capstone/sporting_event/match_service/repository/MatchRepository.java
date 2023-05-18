package com.capstone.sporting_event.match_service.repository;

import com.capstone.sporting_event.match_service.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

}