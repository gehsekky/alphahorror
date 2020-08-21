package com.iamchung.alphahorror.repository;

import com.iamchung.alphahorror.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Player findByGameId(UUID gameId);
}
