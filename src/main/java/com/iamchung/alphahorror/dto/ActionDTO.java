package com.iamchung.alphahorror.dto;

import com.iamchung.alphahorror.model.Action;
import com.iamchung.alphahorror.model.actionChoices.ActionChoice;
import com.iamchung.alphahorror.model.actionValues.ActionValue;

import java.util.UUID;

public class ActionDTO {
    private UUID actionId;
    private String actionType;
    private ActionValue actionValue;
    private ActionChoice resolution;

    public UUID getActionId() {
        return actionId;
    }

    public void setActionId(UUID actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public ActionValue getActionValue() {
        return actionValue;
    }

    public void setActionValue(ActionValue actionValue) {
        this.actionValue = actionValue;
    }

    public ActionChoice getResolution() {
        return resolution;
    }

    public void setResolution(ActionChoice resolution) {
        this.resolution = resolution;
    }

    public static ActionDTO from(Action action) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setActionId(action.getActionId());
        actionDTO.setActionType(action.getActionType());
        actionDTO.setActionValue(action.getActionValue());
        actionDTO.setResolution(action.getResolution());
        return actionDTO;
    }
}
