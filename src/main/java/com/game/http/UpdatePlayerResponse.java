package com.game.http;

import com.game.entity.Profession;
import com.game.entity.Race;

public class UpdatePlayerResponse {
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

    public UpdatePlayerResponse(Long id,
                                   String name,
                                   String title,
                                   Race race,
                                   Profession profession,
                                   Long birthday,
                                   Boolean banned,
                                   Integer experience,
                                   Integer level,
                                   Integer untilNextLevel) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }
}
