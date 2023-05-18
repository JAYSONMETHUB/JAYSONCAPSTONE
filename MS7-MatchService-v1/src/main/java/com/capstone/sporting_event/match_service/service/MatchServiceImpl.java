package com.capstone.sporting_event.match_service.service;

import com.capstone.sporting_event.match_service.dto.MatchMapper;
import com.capstone.sporting_event.match_service.dto.MatchRequestDTO;
import com.capstone.sporting_event.match_service.entity.Match;
import com.capstone.sporting_event.match_service.exceptions.MatchNotFoundException;
import com.capstone.sporting_event.match_service.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    MatchMapper matchMapper;

    public Match saveMatch(MatchRequestDTO matchDTO){

//        if(matchRepo.findByMatchNameIs(matchDTO.getMatchName()).isPresent())
//            throw new MatchAlreadyExistException();

       return matchRepo.save(matchMapper.toEntity(matchDTO));
    }


    public List<Match> getAllMatchs() {
        return matchRepo.findAll();
    }



    public Match getMatch(int id) {

        Optional<Match> match = matchRepo.findById(id);

        if(match.isEmpty()){
            throw new MatchNotFoundException();
        }

        return match.get();
    }




    public Match updateMatch(int id, MatchRequestDTO matchDTO){

        Optional<Match> match = matchRepo.findById(id);

        String teamsId =   IntStream.of(matchDTO.getTeamsId())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        if(match.isEmpty()){
            throw new MatchNotFoundException();
        }

        Match updatedMatch = match.get();
        updatedMatch.setTeamsId(teamsId);
        updatedMatch.setDateTime(matchDTO.getDateTime());
        updatedMatch.setFieldId(matchDTO.getFieldId());
        updatedMatch.setTournamentId(matchDTO.getTournamentId());

        return matchRepo.save(updatedMatch);
    }

    public void deleteMatch(int id) {
        Optional<Match> match = matchRepo.findById(id);

        if(match.isEmpty()) {
            throw new MatchNotFoundException();
        }

        Match deletedMatch = match.get();

        deletedMatch.setActive(false);
        matchRepo.save(deletedMatch);
    }


}
