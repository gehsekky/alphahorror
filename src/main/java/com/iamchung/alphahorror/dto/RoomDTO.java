package com.iamchung.alphahorror.dto;

import com.iamchung.alphahorror.model.Room;

import java.util.UUID;

public class RoomDTO {
    private UUID roomId;
    private Integer coordinateX;
    private Integer coordinateY;
    private String description;
    private Boolean isCleared;
    private Boolean isCurrent;

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public Integer getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Integer coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Integer getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Integer coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCleared() {
        return isCleared;
    }

    public void setCleared(Boolean cleared) {
        isCleared = cleared;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public static RoomDTO from(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.roomId = room.getRoomId();
        roomDTO.coordinateX = room.getCoordinateX();
        roomDTO.coordinateY = room.getCoordinateY();
        roomDTO.description = room.getDescription();
        roomDTO.isCleared = room.getCleared();
        roomDTO.isCurrent = room.getCurrent();
        return roomDTO;
    }
}
