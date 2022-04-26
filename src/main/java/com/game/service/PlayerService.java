package com.game.service;

import com.game.http.*;

import java.util.List;

public interface PlayerService {
    public List<FetchAllPlayersResponse> findAllRegistered(FetchAllPlayersRequest fetchAllPlayersRequest);
    public Integer getPlayersCount(GetPlayersCountRequest getPlayersCountRequest);
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest);
    public FetchPlayerByIdResponse getPlayerById(FetchPlayerByIdRequest fetchPlayerByIdRequest);
    public UpdatePlayerResponse updatePlayer(UpdatePlayerRequest updatePlayerRequest);
    public void deletePlayerById(DeleteByIdRequest deleteByIdRequest);

}
