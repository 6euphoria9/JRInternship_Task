package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.http.CreatePlayerRequest;
import com.game.http.FetchAllPlayersRequest;
import com.game.http.GetPlayersCountRequest;
import com.game.http.UpdatePlayerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface PlayerController {

    @GetMapping
    ResponseEntity<Object> fetchAll(
            @ModelAttribute FetchAllPlayersRequest requestBody
    );

    @GetMapping("/count")
    ResponseEntity<Object> getCount(
            @ModelAttribute GetPlayersCountRequest requestBody
    );

    @PostMapping
    ResponseEntity<Object> createPlayer(
            @RequestBody CreatePlayerRequest requestBody
    );

    @GetMapping("/{id}")
    ResponseEntity<Object> getPlayerById(
            @PathVariable Long id
    );

    @PostMapping("/{id}")
    ResponseEntity<Object> updatePlayer(
            @PathVariable Long id,
            @RequestBody UpdatePlayerRequest requestBody
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletePlayer(
            @PathVariable Long id
    );


}
