package com.game.http;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchAllPlayersRequest {
     String name;
     String title;
     Race race;
     Profession profession;
     Long after;
     Long before;
     Boolean banned;
     Integer minExperience;
     Integer maxExperience;
     Integer minLevel;
     Integer maxLevel;
     PlayerOrder order;
     Integer pageNumber;
     Integer pageSize;
}
