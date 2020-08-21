package com.iamchung.alphahorror.model.actionValues;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.iamchung.alphahorror.model.actionChoices.NavigationChoice;

import java.util.Collections;

@JsonTypeName("spawn")
public class SpawnValue extends ActionValue {

    private String postSpawnDescription;

    public String getPostSpawnDescription() {
        return postSpawnDescription;
    }

    public void setPostSpawnDescription(String postSpawnDescription) {
        this.postSpawnDescription = postSpawnDescription;
    }

    public SpawnValue() throws Exception {
        description = "you wake up on a spaceship. it is dark and the overhead light is blinking on and off. you " +
                "hear screams in the distance.";
        choices = Collections.singletonList(new NavigationChoice("north"));
        postSpawnDescription = "you recognize this is the room you woke up in. nothing has changed.";
    }
}
