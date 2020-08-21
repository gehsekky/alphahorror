package com.iamchung.alphahorror.model.actionValues;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.iamchung.alphahorror.model.actionChoices.ActionChoice;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpawnValue.class, name = "spawn"),
        @JsonSubTypes.Type(value = NavigationValue.class, name="navigation")
})
@MappedSuperclass
public class ActionValue implements Serializable {
    protected String description;
    protected List<ActionChoice> choices;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActionChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<ActionChoice> choices) {
        this.choices = choices;
    }
}
