package com.game.service;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PlayerService {

    ResponseEntity<List<Player>> findAllRegistered(Map<String, String> param);

    Integer getPlayersCount(Map<String, String> param);

    ResponseEntity<Player> createPlayer(Player player);

    ResponseEntity<Player> getPlayerById(Long id);

    ResponseEntity<Player> updatePlayer(Player player, Long id);

    ResponseEntity<HttpStatus> deletePlayerById(Long id);

}
