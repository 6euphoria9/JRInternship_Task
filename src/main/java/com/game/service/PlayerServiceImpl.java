package com.game.service;


import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.http.*;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlayerServiceImpl implements PlayerService {

    PlayerRepository repository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    private static <T> List<T> doPagination(List<T> list, Integer pageSize, Integer pageNumber) {
        if (list.isEmpty()) {
            return list;
        }

        int countOfPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        List<List<T>> resultList = new ArrayList<>(countOfPages);

        for (int pageNum = 0; pageNum < countOfPages; ) {
            resultList.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        }

        return resultList.get(pageNumber);
    }


    @Override
    public List<FetchAllPlayersResponse> findAllRegistered(FetchAllPlayersRequest fetchAllPlayersRequest) {
        List<Player> startPlayerList = new ArrayList<>(repository.findAll());
        Map<String, Object> filters = new HashMap<>();
        Stream<Player> streamPlayer = startPlayerList
                .stream();
        List<FetchAllPlayersResponse> resultList = new ArrayList<>();


        if (fetchAllPlayersRequest.getName() != null) {
            filters.put("name", fetchAllPlayersRequest.getName());
        }
        if (fetchAllPlayersRequest.getTitle() != null) {
            filters.put("title", fetchAllPlayersRequest.getTitle());
        }
        if (fetchAllPlayersRequest.getRace() != null) {
            filters.put("race", fetchAllPlayersRequest.getRace());
        }
        if (fetchAllPlayersRequest.getProfession() != null) {
            filters.put("profession", fetchAllPlayersRequest.getProfession());
        }
        if (fetchAllPlayersRequest.getBanned() != null) {
            filters.put("banned", fetchAllPlayersRequest.getBanned());
        }
        if (fetchAllPlayersRequest.getMinLevel() != null) {
            filters.put("minLevel", fetchAllPlayersRequest.getMinLevel());
        }
        if (fetchAllPlayersRequest.getMaxLevel() != null) {
            filters.put("maxLevel", fetchAllPlayersRequest.getMaxLevel());
        }
        if (fetchAllPlayersRequest.getMinExperience() != null) {
            filters.put("minExperience", fetchAllPlayersRequest.getMinExperience());
        }
        if (fetchAllPlayersRequest.getMaxExperience() != null) {
            filters.put("maxExperience", fetchAllPlayersRequest.getMaxExperience());
        }
        if (fetchAllPlayersRequest.getBefore() != null) {
            filters.put("before", fetchAllPlayersRequest.getBefore());
        }
        if (fetchAllPlayersRequest.getAfter() != null) {
            filters.put("after", fetchAllPlayersRequest.getAfter());
        }

        for (Map.Entry<String, Object> element : filters.entrySet()) {
            if (element.getKey().equals("name")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getName().toLowerCase().contains(String.valueOf(element.getValue()).toLowerCase()));
                continue;
            }
            if (element.getKey().equals("title")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getTitle().toLowerCase().contains(String.valueOf(element.getValue()).toLowerCase()));
                continue;
            }
            if (element.getKey().equals("race")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getRace() == element.getValue());
                continue;
            }
            if (element.getKey().equals("profession")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getProfession() == element.getValue());
                continue;
            }
            if (element.getKey().equals("banned")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getBanned() == element.getValue());
                continue;
            }
            if (element.getKey().equals("minLevel")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getLevel() >= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("maxLevel")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getLevel() <= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("minExperience")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getExperience() >= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("maxExperience")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getExperience() <= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("before")) {
                streamPlayer = streamPlayer.filter(dto -> {
                    Calendar date = Calendar.getInstance();
                    date.setTimeInMillis((long) element.getValue());
                    return dto.getBirthday().before(date);
                });
                continue;
            }
            if (element.getKey().equals("after")) {
                streamPlayer = streamPlayer.filter(dto -> {
                    Calendar date = Calendar.getInstance();
                    date.setTimeInMillis((long) element.getValue());
                    return dto.getBirthday().after(date);
                });
            }
        }

        startPlayerList = streamPlayer.collect(Collectors.toList());

        if (fetchAllPlayersRequest.getOrder() == null) {
            startPlayerList.sort(Comparator.comparing(Player::getId));
        } else {
            switch (fetchAllPlayersRequest.getOrder()) {
                case NAME:
                    startPlayerList.sort(Comparator.comparing(Player::getName));
                    break;
                case LEVEL:
                    startPlayerList.sort(Comparator.comparing(Player::getLevel));
                    break;
                case BIRTHDAY:
                    startPlayerList.sort(Comparator.comparing(Player::getBirthday));
                    break;
                case EXPERIENCE:
                    startPlayerList.sort(Comparator.comparing(Player::getExperience));
                    break;
            }
        }

        if (fetchAllPlayersRequest.getPageSize() == null && fetchAllPlayersRequest.getPageNumber() == null) {
            startPlayerList = doPagination(startPlayerList, 3, 0);
        } else if (fetchAllPlayersRequest.getPageSize() != null && fetchAllPlayersRequest.getPageNumber() == null) {
            startPlayerList = doPagination(startPlayerList, fetchAllPlayersRequest.getPageSize(), 0);
        } else if (fetchAllPlayersRequest.getPageSize() == null && fetchAllPlayersRequest.getPageNumber() != null) {
            startPlayerList = doPagination(startPlayerList, 3, fetchAllPlayersRequest.getPageNumber());
        } else {
            startPlayerList = doPagination(startPlayerList, fetchAllPlayersRequest.getPageSize(), fetchAllPlayersRequest.getPageNumber());
        }

        if (filters.isEmpty()) {
            for (Player player : startPlayerList) {
                resultList.add(new FetchAllPlayersResponse(player));
            }
            return resultList;
        }


        for (Player player : startPlayerList) {
            resultList.add(new FetchAllPlayersResponse(player));
        }

        return resultList;
    }

    @Override
    public Integer getPlayersCount(GetPlayersCountRequest getPlayersCountRequest) {
        List<Player> startPlayerList = new ArrayList<>(repository.findAll());
        Map<String, Object> filters = new HashMap<>();
        Stream<Player> streamPlayer = startPlayerList
                .stream();

        if (getPlayersCountRequest.getName() != null) {
            filters.put("name", getPlayersCountRequest.getName());
        }
        if (getPlayersCountRequest.getTitle() != null) {
            filters.put("title", getPlayersCountRequest.getTitle());
        }
        if (getPlayersCountRequest.getRace() != null) {
            filters.put("race", getPlayersCountRequest.getRace());
        }
        if (getPlayersCountRequest.getProfession() != null) {
            filters.put("profession", getPlayersCountRequest.getProfession());
        }
        if (getPlayersCountRequest.getBanned() != null) {
            filters.put("banned", getPlayersCountRequest.getBanned());
        }
        if (getPlayersCountRequest.getMinLevel() != null) {
            filters.put("minLevel", getPlayersCountRequest.getMinLevel());
        }
        if (getPlayersCountRequest.getMaxLevel() != null) {
            filters.put("maxLevel", getPlayersCountRequest.getMaxLevel());
        }
        if (getPlayersCountRequest.getMinExperience() != null) {
            filters.put("minExperience", getPlayersCountRequest.getMinExperience());
        }
        if (getPlayersCountRequest.getMaxExperience() != null) {
            filters.put("maxExperience", getPlayersCountRequest.getMaxExperience());
        }
        if (getPlayersCountRequest.getBefore() != null) {
            filters.put("before", getPlayersCountRequest.getBefore());
        }
        if (getPlayersCountRequest.getAfter() != null) {
            filters.put("after", getPlayersCountRequest.getAfter());
        }

        for (Map.Entry<String, Object> element : filters.entrySet()) {
            if (element.getKey().equals("name")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getName().contains((String) element.getValue()));
                continue;
            }
            if (element.getKey().equals("title")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getTitle().contains((String) element.getValue()));
                continue;
            }
            if (element.getKey().equals("race")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getRace() == element.getValue());
                continue;
            }
            if (element.getKey().equals("profession")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getProfession() == element.getValue());
                continue;
            }
            if (element.getKey().equals("banned")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getBanned() == element.getValue());
                continue;
            }
            if (element.getKey().equals("minLevel")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getLevel() >= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("maxLevel")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getLevel() <= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("minExperience")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getExperience() >= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("maxExperience")) {
                streamPlayer = streamPlayer.filter(dto -> dto.getExperience() <= (int) element.getValue());
                continue;
            }
            if (element.getKey().equals("before")) {
                streamPlayer = streamPlayer.filter(dto -> {
                    Calendar date = Calendar.getInstance();
                    date.setTimeInMillis((long) element.getValue());
                    return dto.getBirthday().before(date);
                });
                continue;
            }
            if (element.getKey().equals("after")) {
                streamPlayer = streamPlayer.filter(dto -> {
                    Calendar date = Calendar.getInstance();
                    date.setTimeInMillis((long) element.getValue());
                    return dto.getBirthday().after(date);
                });
            }
        }

        return (int) streamPlayer.count();
    }

    @Override
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        // условия отбора <------------------------->

        if (createPlayerRequest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if ((createPlayerRequest.getName() == null ||
                !createPlayerRequest.getName().equals("")) &&
                createPlayerRequest.getBirthday() == null &&
                createPlayerRequest.getExperience() == null &&
                createPlayerRequest.getRace() == null &&
                createPlayerRequest.getProfession() == null &&
                createPlayerRequest.getTitle() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (createPlayerRequest.getName().length() > 12 || createPlayerRequest.getTitle().length() > 30) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (createPlayerRequest.getExperience() < 0 || createPlayerRequest.getExperience() > 10_000_000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (createPlayerRequest.getBirthday() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Calendar timeOfBirthday = new GregorianCalendar();
        timeOfBirthday.setTimeInMillis(createPlayerRequest.getBirthday());

        if (timeOfBirthday.get(Calendar.YEAR) < 2000 || timeOfBirthday.get(Calendar.YEAR) > 3000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //  <------------------------->

        Player player = new Player();
        player.setName(createPlayerRequest.getName());
        player.setTitle(createPlayerRequest.getTitle());
        player.setRace(createPlayerRequest.getRace());
        player.setProfession(createPlayerRequest.getProfession());
        player.setBirthday(timeOfBirthday);
        player.setExperience(createPlayerRequest.getExperience());


        // расчёты по формулам
        if (createPlayerRequest.getBanned() == null) {
            player.setBanned(false);
        } else {
            player.setBanned(createPlayerRequest.getBanned());
        }

        Integer level = (int) ((Math.sqrt(2500 + 200 * createPlayerRequest.getExperience()) - 50) / 100);
        player.setLevel(level);

        Integer untilNextLevel = 50 * (level + 1) * (level + 2) - createPlayerRequest.getExperience();
        player.setUntilNextLevel(untilNextLevel);


        Player savedPlayer = repository.save(player);

        System.out.println("");


        return new CreatePlayerResponse(savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getTitle(),
                savedPlayer.getRace(),
                savedPlayer.getProfession(),
                savedPlayer.getBirthday().getTimeInMillis(),
                savedPlayer.getBanned(),
                savedPlayer.getExperience(),
                savedPlayer.getLevel(),
                savedPlayer.getUntilNextLevel());
    }

    @Override
    public FetchPlayerByIdResponse getPlayerById(FetchPlayerByIdRequest fetchPlayerByIdRequest) {
        if (fetchPlayerByIdRequest.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Player player = repository.findById(fetchPlayerByIdRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        return new FetchPlayerByIdResponse(player.getId(),
                player.getName(),
                player.getTitle(),
                player.getRace(),
                player.getProfession(),
                player.getBirthday().getTimeInMillis(),
                player.getBanned(),
                player.getExperience(),
                player.getLevel(),
                player.getUntilNextLevel());
    }

    @Override
    public UpdatePlayerResponse updatePlayer(UpdatePlayerRequest updatePlayerRequest) {
        if (updatePlayerRequest.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Player player = repository.findById(updatePlayerRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (updatePlayerRequest.getName() != null && !updatePlayerRequest.getName().equals(player.getName())) {
            player.setName(updatePlayerRequest.getName());
        }
        if (updatePlayerRequest.getTitle() != null && !updatePlayerRequest.getTitle().equals(player.getTitle())) {
            player.setTitle(updatePlayerRequest.getTitle());
        }
        if (updatePlayerRequest.getRace() != null && updatePlayerRequest.getRace() != player.getRace()) {
            player.setRace(updatePlayerRequest.getRace());
        }
        if (updatePlayerRequest.getProfession() != null && updatePlayerRequest.getProfession() != player.getProfession()) {
            player.setProfession(updatePlayerRequest.getProfession());
        }
        if (updatePlayerRequest.getBirthday() != null && updatePlayerRequest.getBirthday() != player.getBirthday().getTimeInMillis()) {
            Calendar timeOfBirthday = new GregorianCalendar();
            timeOfBirthday.setTimeInMillis(updatePlayerRequest.getBirthday());

            if (timeOfBirthday.get(Calendar.YEAR) < 2000 || timeOfBirthday.get(Calendar.YEAR) > 3000) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            player.setBirthday(timeOfBirthday);
        }
        if (updatePlayerRequest.getBanned() != null && updatePlayerRequest.getBanned() != player.getBanned()) {
            player.setBanned(updatePlayerRequest.getBanned());
        }
        if (updatePlayerRequest.getExperience() != null && !Objects.equals(updatePlayerRequest.getExperience(), player.getExperience())) {
            if (updatePlayerRequest.getExperience() < 0 || updatePlayerRequest.getExperience() > 10_000_000) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            player.setExperience(updatePlayerRequest.getExperience());

            Integer level = (int) ((Math.sqrt(2500 + 200 * updatePlayerRequest.getExperience()) - 50) / 100);
            player.setLevel(level);

            Integer untilNextLevel = 50 * (level + 1) * (level + 2) - updatePlayerRequest.getExperience();
            player.setUntilNextLevel(untilNextLevel);
        }

        Player savedPlayer = repository.save(player);

        return new UpdatePlayerResponse(savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getTitle(),
                savedPlayer.getRace(),
                savedPlayer.getProfession(),
                savedPlayer.getBirthday().getTimeInMillis(),
                savedPlayer.getBanned(),
                savedPlayer.getExperience(),
                savedPlayer.getLevel(),
                savedPlayer.getUntilNextLevel());
    }

    @Override
    public void deletePlayerById(DeleteByIdRequest deleteByIdRequest) {
        if (deleteByIdRequest.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            repository.deleteById(deleteByIdRequest.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
