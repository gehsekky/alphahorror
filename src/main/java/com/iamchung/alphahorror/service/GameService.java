package com.iamchung.alphahorror.service;

import com.iamchung.alphahorror.dto.ActionDTO;
import com.iamchung.alphahorror.dto.GameDTO;
import com.iamchung.alphahorror.dto.PlayerDTO;
import com.iamchung.alphahorror.dto.RoomDTO;
import com.iamchung.alphahorror.model.Action;
import com.iamchung.alphahorror.model.CharacterClass;
import com.iamchung.alphahorror.model.Game;
import com.iamchung.alphahorror.model.Player;
import com.iamchung.alphahorror.model.Room;
import com.iamchung.alphahorror.model.actionChoices.ActionChoice;
import com.iamchung.alphahorror.model.actionValues.ActionValue;
import com.iamchung.alphahorror.model.actionValues.NavigationValue;
import com.iamchung.alphahorror.model.actionValues.SpawnValue;
import com.iamchung.alphahorror.repository.ActionRepository;
import com.iamchung.alphahorror.repository.CharacterClassRepository;
import com.iamchung.alphahorror.repository.GameRepository;
import com.iamchung.alphahorror.repository.PlayerRepository;
import com.iamchung.alphahorror.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService implements GameServiceImpl {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CharacterClassRepository characterClassRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ActionRepository actionRepository;

    /**
     * get a hydrated game object
     *
     * @param gameId
     * @return
     */
    @Override
    public GameDTO getGame(UUID gameId) {
        Player player = playerRepository.findByGameId(gameId);
        GameDTO gameDTO = GameDTO.from(player.getGame());
        gameDTO.setPlayer(PlayerDTO.from(player));
        List<Room> rooms = roomRepository.findByGameId(gameId);
        gameDTO.setRoom(RoomDTO.from(rooms.stream().filter(room -> room.getCurrent() == true).findAny().get()));
        gameDTO.setActions(
                actionRepository.findByGameId(gameId)
                        .stream()
                        .map(action -> ActionDTO.from(action))
                        .collect(Collectors.toList())
        );
        return gameDTO;
    }

    /**
     * create a new game
     *
     * @return
     */
    @Override
    public GameDTO newGame() {
        try {
            // create game
            Game game = new Game();
            game = gameRepository.save(game);

            // create player
            CharacterClass characterClass = characterClassRepository.findByName("fighter");
            Player player = new Player();
            player.setName("jurgen valhalla jones");
            player.setCharaacterClassId(characterClass.getCharacterClassId());
            player.setCharacterClass(characterClass);
            player.setGameId(game.getGameId());
            player.setHitPoints(100);
            player.setSpellPoints(100);
            player = playerRepository.save(player);

            // create room
            Room room = new Room();
            room.setGameId(game.getGameId());
            room.setCoordinateX(0);
            room.setCoordinateY(0);
            room.setDescription("a quiet nondescript room. a large window to the south shows the vastness of space. " +
                    "you see nothing of value.");
            room.setCleared(true);
            room.setCurrent(true);
            room = roomRepository.save(room);

            // create action
            Action action = new Action();
            action.setActionType("spawn");
            action.setActionValue(new SpawnValue());
            action.setGameId(game.getGameId());
            action.setRoomId(room.getRoomId());
            action.setResolution(null);
            action = actionRepository.save(action);

            GameDTO gameDTO = GameDTO.from(game);
            gameDTO.setPlayer(PlayerDTO.from(player));
            gameDTO.setRoom(RoomDTO.from(room));
            gameDTO.setActions(Collections.singletonList(ActionDTO.from(action)));
            return gameDTO;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.getStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not create new game", ex);
        }

    }

    @Override
    public GameDTO updateGame(Game game) {
        return GameDTO.from(gameRepository.save(game));
    }

    @Override
    public void deleteGame(UUID gameId) {
        gameRepository.deleteById(gameId);
    }

    @Override
    public GameDTO resolveAction(UUID gameId, UUID roomId, UUID actionId, ActionChoice actionChoice) {
        try {
            Game game = gameRepository.findById(gameId).get();
            Room room = roomRepository.findById(roomId).get();

            switch (actionChoice.getType()) {
                case "navigation":
                    int coordinateX = room.getCoordinateX();
                    int coordinateY = room.getCoordinateY();

                    room.setCurrent(false);
                    room.setCleared(true);
                    roomRepository.save(room);

                    switch (actionChoice.getName()) {
                        case "north":
                            coordinateY++;
                            break;
                        case "south":
                            coordinateY--;
                            break;
                        case "east":
                            coordinateX++;
                            break;
                        case "west":
                            coordinateX--;
                            break;
                        default:
                            throw new Exception(String.format("unknown navigationChoice name: %s", actionChoice.getName()));
                    }

                    // check if room already exists
                    Room roomCheckForMoving = roomRepository.findByCoordinateXAndCoordinateY(coordinateX, coordinateY);
                    boolean doesRoomExist = roomCheckForMoving != null;
                    if (!doesRoomExist) {
                        roomCheckForMoving = new Room();
                        roomCheckForMoving.setGameId(gameId);
                        roomCheckForMoving.setCoordinateX(coordinateX);
                        roomCheckForMoving.setCoordinateY(coordinateY);
                        roomCheckForMoving.setCleared(false);
                        roomCheckForMoving.setDescription("another random room with no remarkable features. there is " +
                                "nothing of value here.");
                    }
                    roomCheckForMoving.setCurrent(true);
                    roomRepository.save(roomCheckForMoving);

                    // resolve old action
                    Action oldAction = actionRepository.findById(actionId).get();
                    oldAction.setResolution(actionChoice);
                    actionRepository.save(oldAction);

                    // create new action
                    Action action = new Action();
                    action.setRoomId(roomCheckForMoving.getRoomId());
                    action.setActionType("navigation");
                    action.setGameId(gameId);
                    if (!doesRoomExist) {
                        action.setActionValue(new NavigationValue());
                    } else {
                        Action actionFromRoom = actionRepository.findByRoomId(roomCheckForMoving.getRoomId())
                                .stream().findFirst().get();
                        if (actionFromRoom.getActionType().equals("spawn")) {
                            NavigationValue navigationValue = new NavigationValue();
                            navigationValue.setDescription(((SpawnValue)actionFromRoom.getActionValue()).getPostSpawnDescription());
                            navigationValue.setChoices(actionFromRoom.getActionValue().getChoices());
                            action.setActionValue(navigationValue);
                        } else {
                            action.setActionValue(actionFromRoom.getActionValue());
                        }
                    }
                    actionRepository.save(action);
                    break;
                default:
                    throw new Exception(String.format("unknown actionChoice.type: %s", actionChoice.getType()));
            }

            return getGame(gameId);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.getStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not resolve action", ex);
        }
    }
}
