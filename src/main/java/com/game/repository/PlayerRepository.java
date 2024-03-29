package com.game.repository;

import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>,
        PagingAndSortingRepository<Player, Long>,
        JpaSpecificationExecutor<Player> {

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Player> S save(S entity);

    @Override
    Optional<Player> findById(Long aLong);


}
