package com.game.http;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FetchAllPlayersResponse {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;

    public FetchAllPlayersResponse(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.title = player.getTitle();
        this.race = player.getRace();
        this.profession = player.getProfession();
        this.birthday = player.getBirthday().getTimeInMillis();
        this.banned = player.getBanned();
        this.experience = player.getExperience();
        this.level = player.getLevel();
        this.untilNextLevel = player.getUntilNextLevel();
    }
}
