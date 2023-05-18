package com.capstone.sporting_event.tournament_service.service;

import com.capstone.sporting_event.tournament_service.dto.TournamentMapper;
import com.capstone.sporting_event.tournament_service.dto.TournamentRequestDTO;
import com.capstone.sporting_event.tournament_service.entity.Tournament;
import com.capstone.sporting_event.tournament_service.exceptions.TournamentAlreadyExistException;
import com.capstone.sporting_event.tournament_service.exceptions.TournamentNotFoundException;
import com.capstone.sporting_event.tournament_service.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    TournamentRepository tournamentRepo;

    @Autowired
    TournamentMapper tournamentMapper;

    public Tournament saveTournament(TournamentRequestDTO tournamentDTO){

        if(tournamentRepo.findByTournamentNameIs(tournamentDTO.getTournamentName()).isPresent())
            throw new TournamentAlreadyExistException();

       return tournamentRepo.save(tournamentMapper.toEntity(tournamentDTO));
    }


    public List<Tournament> getAllTournaments() {
        return tournamentRepo.findAll();
    }



    public Tournament getTournament(int id) {

        Optional<Tournament> tournament = tournamentRepo.findById(id);

        if(tournament.isEmpty()){
            throw new TournamentNotFoundException();
        }

        return tournament.get();
    }

    public Tournament getTournamentByName(String tournamentName){
        Optional<Tournament> tournament = tournamentRepo.findByTournamentNameIs(tournamentName);

        if(tournament.isEmpty()){
            throw new TournamentNotFoundException();
        }

        return tournament.get();
    }


    public Tournament updateTournament(int id, TournamentRequestDTO tournamentDTO){

        Optional<Tournament> tournament = tournamentRepo.findById(id);

        if(tournament.isEmpty()){
            throw new TournamentNotFoundException();
        }

        Tournament updatedTournament = tournament.get();

        updatedTournament.setTournamentName(tournamentDTO.getTournamentName());
        updatedTournament.setSportsCategory(tournamentDTO.getSportsCategory());
        updatedTournament.setTournamentStyle(tournamentDTO.getTournamentStyle());

        return tournamentRepo.save(updatedTournament);
    }

    public void deleteTournament(int id) {
        Optional<Tournament> tournament = tournamentRepo.findById(id);

        if(tournament.isEmpty()) {
            throw new TournamentNotFoundException();
        }

        Tournament deletedTournament = tournament.get();

        deletedTournament.setActive(false);
        tournamentRepo.save(deletedTournament);
    }


}
