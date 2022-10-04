package com.game.service;


import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.util.PlayerFilterSortPage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final PlayerFilterSortPage filterSortPage;


    public PlayerServiceImpl(PlayerRepository repository, PlayerFilterSortPage filterSortPage) {
        this.repository = repository;
        this.filterSortPage = filterSortPage;
    }

    @Override
    public ResponseEntity<List<Player>> findAllRegistered(Map<String, String> param) {
        List<Player> players = filterSortPage.filterList(param);
        players = filterSortPage.sortList(param, players);
        players = filterSortPage.makePage(param, players);


        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Override
    public Integer getPlayersCount(Map<String, String> param) {
        return null;
    }

    @Override
    public ResponseEntity<Player> createPlayer(Player player) {
        Player savedPlayer = repository.save(player);
        if (savedPlayer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Player> getPlayerById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Player> updatePlayer(Player player, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deletePlayerById(Long id) {
        Player player = repository.findById(id).orElse(null);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
