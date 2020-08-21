package com.iamchung.alphahorror.service;

import com.iamchung.alphahorror.dto.GameDTO;
import com.iamchung.alphahorror.model.Game;
import com.iamchung.alphahorror.model.actionChoices.ActionChoice;

import java.util.UUID;

public interface GameServiceImpl {
    GameDTO getGame(UUID gameId);
    GameDTO newGame();
    GameDTO updateGame(Game game);
    GameDTO resolveAction(UUID gameId, UUID roomId, UUID actionId, ActionChoice actionChoice);
    void deleteGame(UUID gameId);
}
