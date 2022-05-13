package com.game.service;

import com.game.http.*;

import java.util.List;

public interface PlayerService {
    List<FetchAllPlayersResponse> findAllRegistered(FetchAllPlayersRequest fetchAllPlayersRequest);
    Integer getPlayersCount(GetPlayersCountRequest getPlayersCountRequest);
    CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest);
    FetchPlayerByIdResponse getPlayerById(FetchPlayerByIdRequest fetchPlayerByIdRequest);
    UpdatePlayerResponse updatePlayer(UpdatePlayerRequest updatePlayerRequest);
    void deletePlayerById(DeleteByIdRequest deleteByIdRequest);

}
