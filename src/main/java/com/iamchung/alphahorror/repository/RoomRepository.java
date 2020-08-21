package com.iamchung.alphahorror.repository;

import com.iamchung.alphahorror.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    List<Room> findByGameId(UUID gameId);
    Room findByCoordinateXAndCoordinateY(Integer coordinateX, Integer coordinateY);
}
