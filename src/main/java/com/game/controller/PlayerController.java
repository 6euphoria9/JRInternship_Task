package com.game.controller;

import com.game.entity.Player;

import com.game.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ResponseBody
    @GetMapping
    ResponseEntity<List<Player>> fetchAll(
            @RequestParam(required = false) Map<String, String> param
    ) {
        return playerService.findAllRegistered(param);
    }


    @ResponseBody
    @GetMapping("/count")
    Integer getCount(
            @RequestParam(required = false) Map<String, String> param
    ) {
        return playerService.getPlayersCount(param);
    }

    @PostMapping
    ResponseEntity<Player> createPlayer(
            @RequestBody Player player
    ) {
        return playerService.createPlayer(player);
    }

    @ResponseBody
    @GetMapping("/{id}")
    ResponseEntity<Player> getPlayerById(
            @PathVariable("id") Long id
    ) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/{id}")
    ResponseEntity<Player> updatePlayer(
            @RequestBody Player player,
            @PathVariable("id") Long id
    ) {
        return playerService.updatePlayer(player, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deletePlayer(
            @PathVariable Long id
    ) {
        return playerService.deletePlayerById(id);
    }


}
