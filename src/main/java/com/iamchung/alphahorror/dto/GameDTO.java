package com.iamchung.alphahorror.dto;

import com.iamchung.alphahorror.model.Game;

import java.util.List;
import java.util.UUID;

public class GameDTO {
    private UUID gameId;
    private PlayerDTO player;
    private RoomDTO room;
    private List<ActionDTO> actions;

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public List<ActionDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionDTO> actions) {
        this.actions = actions;
    }

    public static GameDTO from(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameId(game.getGameId());
        return gameDTO;
    }
}
