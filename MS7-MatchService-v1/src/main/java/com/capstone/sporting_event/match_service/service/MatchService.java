package com.capstone.sporting_event.match_service.service;

import com.capstone.sporting_event.match_service.dto.MatchRequestDTO;
import com.capstone.sporting_event.match_service.entity.Match;
import com.capstone.sporting_event.match_service.exceptions.MatchAlreadyExistException;
import com.capstone.sporting_event.match_service.exceptions.MatchNotFoundException;

import java.util.List;

public interface MatchService {
    public Match saveMatch(MatchRequestDTO matchDTO) throws MatchAlreadyExistException;

    public List<Match> getAllMatchs();
    public Match getMatch(int id) throws MatchNotFoundException;
    public Match updateMatch(int id, MatchRequestDTO matchDTO) throws MatchNotFoundException;

    public void deleteMatch(int id) throws MatchNotFoundException;

}
