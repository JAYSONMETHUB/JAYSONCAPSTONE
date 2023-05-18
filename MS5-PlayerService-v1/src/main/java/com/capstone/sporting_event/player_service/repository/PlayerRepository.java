package com.capstone.sporting_event.player_service.repository;

import com.capstone.sporting_event.player_service.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByTeamId(int teamId);

}