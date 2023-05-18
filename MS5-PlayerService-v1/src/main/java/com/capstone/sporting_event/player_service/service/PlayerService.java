package com.capstone.sporting_event.player_service.service;

import com.capstone.sporting_event.player_service.dto.PlayerRequestDTO;
import com.capstone.sporting_event.player_service.entity.Player;
import com.capstone.sporting_event.player_service.exceptions.PlayerAlreadyExistsException;
import com.capstone.sporting_event.player_service.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayerService {

    public Player savePlayer(PlayerRequestDTO player) throws PlayerAlreadyExistsException;
    public List<Player> getAllPlayers();

    public List<Player> getPlayersByTeamId(int teamId);
    public List<Player> getPlayersByTeamName(String teamName);

    public Player getPlayer(int id) throws PlayerNotFoundException;
    public Player updatePlayer(int id,PlayerRequestDTO player) throws PlayerNotFoundException;

    public void deletePlayer(int id) throws PlayerNotFoundException;

}
