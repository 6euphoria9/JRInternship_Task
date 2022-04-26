package com.game.http;

import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePlayerResponse {
    Long id;
    String name;

    String title;
    Race race;
    Profession profession;
    Long birthday;
    Boolean banned;
    Integer experience;
    Integer level;
    Integer untilNextLevel;

    public CreatePlayerResponse(Long id,
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
