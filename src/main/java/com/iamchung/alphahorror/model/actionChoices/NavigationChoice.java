package com.iamchung.alphahorror.model.actionChoices;

public class NavigationChoice extends ActionChoice {
    public NavigationChoice(String direction) throws Exception {
        switch (direction) {
            case "north":
            case "south":
            case "east":
            case "west":
                name = direction;
                description = String.format("go %s into the ship", direction);
                type = "navigation";
                break;
            default:
                throw new Exception(String.format("unknown direction: %s", direction));
        }
    }
}
