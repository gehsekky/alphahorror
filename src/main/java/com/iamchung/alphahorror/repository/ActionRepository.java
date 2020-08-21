package com.iamchung.alphahorror.repository;

import com.iamchung.alphahorror.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {
    List<Action> findByGameId(UUID gameId);
    List<Action> findByRoomId(UUID roomId);
}
