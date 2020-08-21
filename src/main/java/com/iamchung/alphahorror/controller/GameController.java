package com.iamchung.alphahorror.controller;

import com.iamchung.alphahorror.dto.GameDTO;
import com.iamchung.alphahorror.model.Action;
import com.iamchung.alphahorror.model.actionChoices.ActionChoice;
import com.iamchung.alphahorror.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameServiceImpl gameService;

    @GetMapping("/{gameId}")
    public GameDTO getGame(@PathVariable("gameId") UUID gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping()
    public GameDTO newGame() {
        return gameService.newGame();
    }

    @PostMapping("/{gameId}/{roomId}/{actionId}")
    public GameDTO resolveAction(
            @PathVariable("gameId") UUID gameId,
            @PathVariable("roomId") UUID roomId,
            @PathVariable("actionId") UUID actionId,
            @RequestBody ActionChoice actionChoice
    ) { return gameService.resolveAction(gameId, roomId, actionId, actionChoice); }
}
