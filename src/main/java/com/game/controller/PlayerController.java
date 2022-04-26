package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.http.CreatePlayerRequest;
import com.game.http.UpdatePlayerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


public interface PlayerController {

    @GetMapping
    ResponseEntity<Object>  fetchAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Race race,
            @RequestParam(required = false) Profession profession,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false) Boolean banned,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) Integer maxExperience,
            @RequestParam(required = false) Integer minLevel,
            @RequestParam(required = false) Integer maxLevel,
            @RequestParam(required = false) PlayerOrder order,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    );

    @GetMapping("/count")
    ResponseEntity<Object>  getCount(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Race race,
            @RequestParam(required = false) Profession profession,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false) Boolean banned,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) Integer maxExperience,
            @RequestParam(required = false) Integer minLevel,
            @RequestParam(required = false) Integer maxLevel
    );

    @PostMapping
    ResponseEntity<Object>  createPlayer(
            @RequestBody CreatePlayerRequest requestBody,
            HttpServletRequest request
    );

    @GetMapping("/{id}")
    ResponseEntity<Object>  getPlayerById(
            @PathVariable Long id
    );

    @PostMapping("/{id}")
    ResponseEntity<Object> updatePlayer(
            @PathVariable Long id,
            @RequestBody UpdatePlayerRequest requestBody,
            HttpServletRequest request
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletePlayer(
            @PathVariable Long id
    );



}
