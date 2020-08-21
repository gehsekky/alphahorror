package com.iamchung.alphahorror.model.actionValues;

import com.iamchung.alphahorror.model.actionChoices.NavigationChoice;

import java.util.Arrays;

public class NavigationValue extends ActionValue {
    public NavigationValue() throws Exception {
        description = "you enter a room that seems eerily similar to the one you just came from.";
        choices = Arrays.asList(
                new NavigationChoice("north"),
                new NavigationChoice("south"),
                new NavigationChoice("east"),
                new NavigationChoice("west")
        );
    }
}
