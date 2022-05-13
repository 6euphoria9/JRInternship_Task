package com.game.controller;

import com.game.http.*;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/players")
public class PlayerControllerImpl implements PlayerController {
    PlayerService service;


    @Autowired
    public PlayerControllerImpl(PlayerService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Object> fetchAll(FetchAllPlayersRequest requestBody) {
        return new ResponseEntity<>(service.findAllRegistered(requestBody), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object>  getCount(GetPlayersCountRequest requestBody) {
        return new ResponseEntity<>(service.getPlayersCount(requestBody), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createPlayer(CreatePlayerRequest requestBody) {
        return new ResponseEntity<>(service.createPlayer(requestBody), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> getPlayerById(Long id) {
        FetchPlayerByIdRequest request = new FetchPlayerByIdRequest(id);
        return new ResponseEntity<>(service.getPlayerById(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updatePlayer(Long id, UpdatePlayerRequest requestBody) {
        requestBody.setId(id);
        return new ResponseEntity<>(service.updatePlayer(requestBody), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deletePlayer(Long id) {
        DeleteByIdRequest request = new DeleteByIdRequest(id);
        service.deletePlayerById(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
