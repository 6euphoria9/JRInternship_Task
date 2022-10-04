package com.game.util;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Component
public class PlayerFilterSortPage {

    private final PlayerRepository repository;

    public PlayerFilterSortPage(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> sortList(Map<String, String> params, List<Player> playerList) {
        PlayerOrder order;


        if (params.containsKey("order")) {
            order = PlayerOrder.valueOf(params.get("order"));
            switch (order) {
                case ID:
                    playerList.sort(Comparator.comparing(Player::getId));
                    break;
                case NAME:
                    playerList.sort(Comparator.comparing(Player::getName));
                    break;
                case LEVEL:
                    playerList.sort(Comparator.comparing(Player::getLevel));
                    break;
                case BIRTHDAY:
                    playerList.sort(Comparator.comparing(Player::getBirthday));
                    break;
                case EXPERIENCE:
                    playerList.sort(Comparator.comparing(Player::getExperience));
                    break;
            }
        } else {
            playerList.sort(Comparator.comparing(Player::getId));
        }

        return playerList;
    }

    public List<Player> makePage(Map<String, String> params, List<Player> players) {
        Integer pageSize = 3;
        Integer pageNumber = 0;

        if (params.containsKey("pageSize")) {
            pageSize = Integer.valueOf(params.get("pageSize"));
        }
        if (params.containsKey("pageNumber")) {
            pageNumber = Integer.valueOf(params.get("pageNumber"));
        }

//        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        PagedListHolder<Player> pagedList = new PagedListHolder<>(players);
        pagedList.setPageSize(pageSize);
        pagedList.setPage(pageNumber);

        return pagedList.getPageList();

//        return filterList(params, pageable);
    }

    public List<Player> filterList(Map<String, String> params) {
        Specification<Player> querySpecifitcation = new Specification<Player>() {

            @Override
            public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (params.containsKey("name")) {
                    String name = params.get("name");
                    predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
                }
                if (params.containsKey("title")) {
                    String title = params.get("title");
                    predicates.add(criteriaBuilder.like(root.get("title"), "%"+title+"%"));
                }
                if (params.containsKey("race")) {
                    Race race = Race.valueOf(params.get("race"));
                    predicates.add(criteriaBuilder.equal(root.get("race"), race));
                }
                if (params.containsKey("profession")) {
                    Profession profession = Profession.valueOf(params.get("profession"));
                    predicates.add(criteriaBuilder.equal(root.get("profession"), profession));
                }
                if (params.containsKey("after")) {
                    Calendar after = Calendar.getInstance();
                    after.setTimeInMillis(Long.parseLong(params.get("after")));
                    predicates.add(criteriaBuilder.greaterThan(root.get("birthday"), after));
                }
                if (params.containsKey("before")) {
//                    Date before = new Date(Long.parseLong(params.get("before")) * 1000);
//                    Date before = Date.from(Instant.parse(params.get("before")));

                    Calendar before = Calendar.getInstance();
                    before.setTimeInMillis(Long.parseLong(params.get("before")));
                    predicates.add(criteriaBuilder.lessThan(root.get("birthday"), before));
                }
                if (params.containsKey("banned")) {
                    Boolean banned = Boolean.valueOf(params.get("banned"));
                    predicates.add(criteriaBuilder.equal(root.get("banned"), banned));
                }
                if (params.containsKey("minExperience")) {
                    Integer minExperience = Integer.valueOf(params.get("minExperience"));
                    predicates.add(criteriaBuilder.greaterThan(root.get("experience"), minExperience));
                }
                if (params.containsKey("maxExperience")) {
                    Integer maxExperience = Integer.valueOf(params.get("maxExperience"));
                    predicates.add(criteriaBuilder.lessThan(root.get("experience"), maxExperience));
                }
                if (params.containsKey("minLevel")) {
                    Integer minLevel = Integer.valueOf(params.get("minLevel"));
                    predicates.add(criteriaBuilder.greaterThan(root.get("level"), minLevel));
                }
                if (params.containsKey("maxLevel")) {
                    Integer maxLevel = Integer.valueOf(params.get("maxLevel"));
                    predicates.add(criteriaBuilder.lessThan(root.get("level"), maxLevel));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return repository.findAll(querySpecifitcation);
    }

}

