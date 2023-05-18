package com.capstone.sporting_event.team_service.repository;

import com.capstone.sporting_event.team_service.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByTeamNameIs(String teamName);


}