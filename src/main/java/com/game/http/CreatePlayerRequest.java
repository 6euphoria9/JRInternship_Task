package com.game.http;

import com.game.entity.Profession;
import com.game.entity.Race;

public class CreatePlayerRequest {
    String name;
    String title;
    Race race;
    Profession profession;
    Long birthday;
    Boolean banned;
    Integer experience;

    public CreatePlayerRequest(String name, String title, Race race, Profession profession, Long birthday, Boolean banned, Integer experience) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
    }

    public CreatePlayerRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public Race getRace() {
        return this.race;
    }

    public Profession getProfession() {
        return this.profession;
    }

    public Long getBirthday() {
        return this.birthday;
    }

    public Boolean getBanned() {
        return this.banned;
    }

    public Integer getExperience() {
        return this.experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreatePlayerRequest)) return false;
        final CreatePlayerRequest other = (CreatePlayerRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$race = this.getRace();
        final Object other$race = other.getRace();
        if (this$race == null ? other$race != null : !this$race.equals(other$race)) return false;
        final Object this$profession = this.getProfession();
        final Object other$profession = other.getProfession();
        if (this$profession == null ? other$profession != null : !this$profession.equals(other$profession))
            return false;
        final Object this$birthday = this.getBirthday();
        final Object other$birthday = other.getBirthday();
        if (this$birthday == null ? other$birthday != null : !this$birthday.equals(other$birthday)) return false;
        final Object this$banned = this.getBanned();
        final Object other$banned = other.getBanned();
        if (this$banned == null ? other$banned != null : !this$banned.equals(other$banned)) return false;
        final Object this$experience = this.getExperience();
        final Object other$experience = other.getExperience();
        if (this$experience == null ? other$experience != null : !this$experience.equals(other$experience))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreatePlayerRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $race = this.getRace();
        result = result * PRIME + ($race == null ? 43 : $race.hashCode());
        final Object $profession = this.getProfession();
        result = result * PRIME + ($profession == null ? 43 : $profession.hashCode());
        final Object $birthday = this.getBirthday();
        result = result * PRIME + ($birthday == null ? 43 : $birthday.hashCode());
        final Object $banned = this.getBanned();
        result = result * PRIME + ($banned == null ? 43 : $banned.hashCode());
        final Object $experience = this.getExperience();
        result = result * PRIME + ($experience == null ? 43 : $experience.hashCode());
        return result;
    }

    public String toString() {
        return "CreatePlayerRequest(name=" + this.getName() + ", title=" + this.getTitle() + ", race=" + this.getRace() + ", profession=" + this.getProfession() + ", birthday=" + this.getBirthday() + ", banned=" + this.getBanned() + ", experience=" + this.getExperience() + ")";
    }
}
