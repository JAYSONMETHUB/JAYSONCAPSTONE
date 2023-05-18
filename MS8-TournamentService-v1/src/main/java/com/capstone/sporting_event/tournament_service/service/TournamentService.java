package com.capstone.sporting_event.tournament_service.service;

import com.capstone.sporting_event.tournament_service.dto.TournamentRequestDTO;
import com.capstone.sporting_event.tournament_service.entity.Tournament;
import com.capstone.sporting_event.tournament_service.exceptions.TournamentAlreadyExistException;
import com.capstone.sporting_event.tournament_service.exceptions.TournamentNotFoundException;

import java.util.List;

public interface TournamentService {
    public Tournament saveTournament(TournamentRequestDTO tournamentDTO) throws TournamentAlreadyExistException;

    public List<Tournament> getAllTournaments();
    public Tournament getTournament(int id) throws TournamentNotFoundException;
    public Tournament getTournamentByName(String name) throws TournamentNotFoundException;

    public Tournament updateTournament(int id, TournamentRequestDTO tournamentDTO) throws TournamentNotFoundException;

    public void deleteTournament(int id) throws TournamentNotFoundException;

}
