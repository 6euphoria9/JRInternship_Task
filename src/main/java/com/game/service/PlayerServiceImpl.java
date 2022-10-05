package com.game.service;


import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.util.CalculatePlayer;
import com.game.util.PlayerFilterSortPage;

import com.game.util.ValidatePlayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final PlayerFilterSortPage filterSortPage;
    private final ValidatePlayer validator;
    private final CalculatePlayer calculatePlayer;


    public PlayerServiceImpl(PlayerRepository repository, PlayerFilterSortPage filterSortPage, ValidatePlayer validator, CalculatePlayer calculatePlayer) {
        this.repository = repository;
        this.filterSortPage = filterSortPage;
        this.validator = validator;
        this.calculatePlayer = calculatePlayer;
    }

    @Override
    public ResponseEntity<List<Player>> findAllRegistered(Map<String, String> param) {
        List<Player> players = filterSortPage.filterList(param);
        filterSortPage.sortList(param, players);
        players = filterSortPage.makePage(param, players);


        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Override
    public Integer getPlayersCount(Map<String, String> param) {
        List<Player> players = filterSortPage.filterList(param);

        return players.size();
    }

    @Override
    public ResponseEntity<Player> createPlayer(Player player) {
        if (validator.validatePlayerForCreate(player)) {
            calculatePlayer.resetLevelAndExperience(player);
            repository.save(player);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Player> getPlayerById(Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = repository.findById(id).orElse(null);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Player> updatePlayer(Player updatedPlayer, Long id) {
        if (id == null || id <= 0 || !validator.validateForUpdate(updatedPlayer))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Player> playerFromDB = repository.findById(id);
        if (playerFromDB.isPresent()) {
            Player oldPlayer = playerFromDB.get();
            calculatePlayer.updatePlayer(oldPlayer, updatedPlayer);
            repository.save(oldPlayer);
            return new ResponseEntity<>(oldPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deletePlayerById(Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = repository.findById(id).orElse(null);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
