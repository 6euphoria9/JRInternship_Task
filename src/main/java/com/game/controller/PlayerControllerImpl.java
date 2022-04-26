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
    org.slf4j.Logger log = LoggerFactory.getLogger(PlayerControllerImpl.class);
    PlayerService service;


    @Autowired
    public PlayerControllerImpl(PlayerService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Object> fetchAll(String name,
                                                  String title,
                                                  Race race,
                                                  Profession profession,
                                                  Long after,
                                                  Long before,
                                                  Boolean banned,
                                                  Integer minExperience,
                                                  Integer maxExperience,
                                                  Integer minLevel,
                                                  Integer maxLevel,
                                                  PlayerOrder order,
                                                  Integer pageNumber,
                                                  Integer pageSize) {
        FetchAllPlayersRequest request = new FetchAllPlayersRequest(name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel,
                order,
                pageNumber,
                pageSize);

        return new ResponseEntity<>(service.findAllRegistered(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object>  getCount(String name,
                            String title,
                            Race race,
                            Profession profession,
                            Long after,
                            Long before,
                            Boolean banned,
                            Integer minExperience,
                            Integer maxExperience,
                            Integer minLevel,
                            Integer maxLevel) {
        GetPlayersCountRequest request = new GetPlayersCountRequest(name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel);
        return new ResponseEntity<>(service.getPlayersCount(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createPlayer(CreatePlayerRequest requestBody, HttpServletRequest request) {
        return new ResponseEntity<>(service.createPlayer(requestBody), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> getPlayerById(Long id) {
        FetchPlayerByIdRequest request = new FetchPlayerByIdRequest(id);
        return new ResponseEntity<>(service.getPlayerById(request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updatePlayer(Long id, UpdatePlayerRequest requestBody, HttpServletRequest request) {
        requestBody.setId(id);
        return new ResponseEntity<>(service.updatePlayer(requestBody), HttpStatus.OK);
    }


    //    @Override
//    public ResponseEntity<Object> updatePlayer(Long id,
//                                               String name,
//                                               String title,
//                                               Race race,
//                                               Profession profession,
//                                               Long birthday,
//                                               Boolean banned,
//                                               Integer experience) {
//        UpdatePlayerRequest request = new UpdatePlayerRequest(id,
//                name,
//                title,
//                race,
//                profession,
//                birthday,
//                banned,
//                experience);
//
//        return new ResponseEntity<>(service.updatePlayer(request), HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<Object> deletePlayer(Long id) {
        log.info("Delete player");
        DeleteByIdRequest request = new DeleteByIdRequest(id);
        service.deletePlayerById(request);
        return ResponseEntity.ok().build();
    }
}
