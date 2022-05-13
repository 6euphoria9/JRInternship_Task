package com.game.http;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;

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

     public FetchAllPlayersRequest() {
     }

     public FetchAllPlayersRequest(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel, PlayerOrder order, Integer pageNumber, Integer pageSize) {
          this.name = name;
          this.title = title;
          this.race = race;
          this.profession = profession;
          this.after = after;
          this.before = before;
          this.banned = banned;
          this.minExperience = minExperience;
          this.maxExperience = maxExperience;
          this.minLevel = minLevel;
          this.maxLevel = maxLevel;
          this.order = order;
          this.pageNumber = pageNumber;
          this.pageSize = pageSize;
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

     public Long getAfter() {
          return this.after;
     }

     public Long getBefore() {
          return this.before;
     }

     public Boolean getBanned() {
          return this.banned;
     }

     public Integer getMinExperience() {
          return this.minExperience;
     }

     public Integer getMaxExperience() {
          return this.maxExperience;
     }

     public Integer getMinLevel() {
          return this.minLevel;
     }

     public Integer getMaxLevel() {
          return this.maxLevel;
     }

     public PlayerOrder getOrder() {
          return this.order;
     }

     public Integer getPageNumber() {
          return this.pageNumber;
     }

     public Integer getPageSize() {
          return this.pageSize;
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

     public void setAfter(Long after) {
          this.after = after;
     }

     public void setBefore(Long before) {
          this.before = before;
     }

     public void setBanned(Boolean banned) {
          this.banned = banned;
     }

     public void setMinExperience(Integer minExperience) {
          this.minExperience = minExperience;
     }

     public void setMaxExperience(Integer maxExperience) {
          this.maxExperience = maxExperience;
     }

     public void setMinLevel(Integer minLevel) {
          this.minLevel = minLevel;
     }

     public void setMaxLevel(Integer maxLevel) {
          this.maxLevel = maxLevel;
     }

     public void setOrder(PlayerOrder order) {
          this.order = order;
     }

     public void setPageNumber(Integer pageNumber) {
          this.pageNumber = pageNumber;
     }

     public void setPageSize(Integer pageSize) {
          this.pageSize = pageSize;
     }

     public boolean equals(final Object o) {
          if (o == this) return true;
          if (!(o instanceof FetchAllPlayersRequest)) return false;
          final FetchAllPlayersRequest other = (FetchAllPlayersRequest) o;
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
          final Object this$after = this.getAfter();
          final Object other$after = other.getAfter();
          if (this$after == null ? other$after != null : !this$after.equals(other$after)) return false;
          final Object this$before = this.getBefore();
          final Object other$before = other.getBefore();
          if (this$before == null ? other$before != null : !this$before.equals(other$before)) return false;
          final Object this$banned = this.getBanned();
          final Object other$banned = other.getBanned();
          if (this$banned == null ? other$banned != null : !this$banned.equals(other$banned)) return false;
          final Object this$minExperience = this.getMinExperience();
          final Object other$minExperience = other.getMinExperience();
          if (this$minExperience == null ? other$minExperience != null : !this$minExperience.equals(other$minExperience))
               return false;
          final Object this$maxExperience = this.getMaxExperience();
          final Object other$maxExperience = other.getMaxExperience();
          if (this$maxExperience == null ? other$maxExperience != null : !this$maxExperience.equals(other$maxExperience))
               return false;
          final Object this$minLevel = this.getMinLevel();
          final Object other$minLevel = other.getMinLevel();
          if (this$minLevel == null ? other$minLevel != null : !this$minLevel.equals(other$minLevel)) return false;
          final Object this$maxLevel = this.getMaxLevel();
          final Object other$maxLevel = other.getMaxLevel();
          if (this$maxLevel == null ? other$maxLevel != null : !this$maxLevel.equals(other$maxLevel)) return false;
          final Object this$order = this.getOrder();
          final Object other$order = other.getOrder();
          if (this$order == null ? other$order != null : !this$order.equals(other$order)) return false;
          final Object this$pageNumber = this.getPageNumber();
          final Object other$pageNumber = other.getPageNumber();
          if (this$pageNumber == null ? other$pageNumber != null : !this$pageNumber.equals(other$pageNumber))
               return false;
          final Object this$pageSize = this.getPageSize();
          final Object other$pageSize = other.getPageSize();
          if (this$pageSize == null ? other$pageSize != null : !this$pageSize.equals(other$pageSize)) return false;
          return true;
     }

     protected boolean canEqual(final Object other) {
          return other instanceof FetchAllPlayersRequest;
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
          final Object $after = this.getAfter();
          result = result * PRIME + ($after == null ? 43 : $after.hashCode());
          final Object $before = this.getBefore();
          result = result * PRIME + ($before == null ? 43 : $before.hashCode());
          final Object $banned = this.getBanned();
          result = result * PRIME + ($banned == null ? 43 : $banned.hashCode());
          final Object $minExperience = this.getMinExperience();
          result = result * PRIME + ($minExperience == null ? 43 : $minExperience.hashCode());
          final Object $maxExperience = this.getMaxExperience();
          result = result * PRIME + ($maxExperience == null ? 43 : $maxExperience.hashCode());
          final Object $minLevel = this.getMinLevel();
          result = result * PRIME + ($minLevel == null ? 43 : $minLevel.hashCode());
          final Object $maxLevel = this.getMaxLevel();
          result = result * PRIME + ($maxLevel == null ? 43 : $maxLevel.hashCode());
          final Object $order = this.getOrder();
          result = result * PRIME + ($order == null ? 43 : $order.hashCode());
          final Object $pageNumber = this.getPageNumber();
          result = result * PRIME + ($pageNumber == null ? 43 : $pageNumber.hashCode());
          final Object $pageSize = this.getPageSize();
          result = result * PRIME + ($pageSize == null ? 43 : $pageSize.hashCode());
          return result;
     }

     public String toString() {
          return "FetchAllPlayersRequest(name=" + this.getName() + ", title=" + this.getTitle() + ", race=" + this.getRace() + ", profession=" + this.getProfession() + ", after=" + this.getAfter() + ", before=" + this.getBefore() + ", banned=" + this.getBanned() + ", minExperience=" + this.getMinExperience() + ", maxExperience=" + this.getMaxExperience() + ", minLevel=" + this.getMinLevel() + ", maxLevel=" + this.getMaxLevel() + ", order=" + this.getOrder() + ", pageNumber=" + this.getPageNumber() + ", pageSize=" + this.getPageSize() + ")";
     }
}
