package com.capstone.sporting_event.player_service.service;

import com.capstone.sporting_event.player_service.dto.PlayerRequestDTO;
import com.capstone.sporting_event.player_service.entity.Player;
import com.capstone.sporting_event.player_service.exceptions.PlayerNotFoundException;
import com.capstone.sporting_event.player_service.feign.TeamClient;
import com.capstone.sporting_event.player_service.models.Team;
import com.capstone.sporting_event.player_service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    TeamClient teamClient;



    public Player savePlayer(PlayerRequestDTO playerDTO) {



        if(playerDTO.getTeamName()!=null) {

            Team getTeamOfPlayer = teamClient.getTeamByName(playerDTO.getTeamName());

            Integer teamId = getTeamOfPlayer.getTeamId();

            Player newPlayer = Player.builder()
                    .firstName(playerDTO.getFirstName())
                    .lastName(playerDTO.getLastName())
                    .country(playerDTO.getCountry())
                    .teamId(teamId)
                    .build();



            return playerRepo.save(newPlayer);

        }


        else{

            Player newPlayer = Player.builder()
                    .firstName(playerDTO.getFirstName())
                    .lastName(playerDTO.getLastName())
                    .country(playerDTO.getCountry())
                    .build();
            return playerRepo.save(newPlayer);

        }

    }

    public Player getPlayer(int id) {
        Optional<Player> player = playerRepo.findById(id);

        if(player.isEmpty()){
            throw new PlayerNotFoundException();
        }

        return player.get();
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }
    public List<Player> getPlayersByTeamId(int teamId){
        return playerRepo.findByTeamId(teamId);
    }

    public List<Player> getPlayersByTeamName(String teamName){
        int teamId = teamClient.getTeamByName(teamName).getTeamId();
        return playerRepo.findByTeamId(teamId);
    }
    public Player updatePlayer(int id, PlayerRequestDTO playerDTO){


        int teamId = teamClient.getTeamByName(playerDTO.getTeamName()).getTeamId();

        Optional<Player> user = playerRepo.findById(id);

        if(user.isEmpty()){
            throw new PlayerNotFoundException();
        }

        Player updatedPlayer = user.get();

        updatedPlayer.setFirstName(playerDTO.getFirstName());
        updatedPlayer.setLastName(playerDTO.getLastName());
        updatedPlayer.setCountry(playerDTO.getCountry());
        updatedPlayer.setTeamId(teamId);

        return playerRepo.save(updatedPlayer);

    }

    public void deletePlayer(int id) {

        Optional<Player> user = playerRepo.findById(id);

        if(user.isEmpty()){
            throw new PlayerNotFoundException();
        }

        Player deletedUser = user.get();
        deletedUser.setActive(false);
        playerRepo.save(deletedUser);

    }


}
