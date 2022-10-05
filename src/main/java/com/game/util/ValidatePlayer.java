package com.game.util;

import com.game.entity.Player;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ValidatePlayer {

    public boolean validatePlayerForCreate(Player player) {
        String name = player.getName();
        String title = player.getTitle();
        Integer experience = player.getExperience();
        Calendar birthday = player.getBirthday();

        if (name == null || name.length() > 12 || name.equals("")) {
            return false;
        } else if (title.length() > 30) {
            return false;
        } else if (experience == null || experience > 10_000_000 || experience < 0) {
            return false;
        } else return birthday.getTime().getYear() >= (2000 - 1900) && birthday.getTime().getYear() <= (3000 - 1900);
    }

    public boolean validateForUpdate(Player player) {

        String name = player.getName();
        String title = player.getTitle();
        Integer experience = player.getExperience();
        Calendar birthday = player.getBirthday();

        if (name != null)
            if (name.length() > 12 || name.equals("")) {
                return false;
            }
        if (title != null)
            if (title.length() > 30) {
                return false;
            }
        if (experience != null)
            if (experience > 10_000_000 || experience < 0) {
                return false;
            }
        if (birthday != null)
            if (birthday.getTime().getYear() < (2000 - 1900) || birthday.getTime().getYear() > (3000 - 1900)) {
                return false;
            }

        return true;

    }
}
