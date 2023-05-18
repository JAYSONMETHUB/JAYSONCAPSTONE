package com.capstone.sporting_event.tournament_service.repository;

import com.capstone.sporting_event.tournament_service.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Optional<Tournament> findByTournamentNameIs(String tournamentName);


}