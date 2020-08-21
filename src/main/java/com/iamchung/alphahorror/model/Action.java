package com.iamchung.alphahorror.model;

import com.iamchung.alphahorror.model.actionChoices.ActionChoice;
import com.iamchung.alphahorror.model.actionValues.ActionValue;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "action")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "action_id")
    private UUID actionId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "action_value", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private ActionValue actionValue;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "game_id")
    private UUID gameId;

    @Column(name = "resolution", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private ActionChoice resolution;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Timestamp updatedAt;

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

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public ActionChoice getResolution() {
        return resolution;
    }

    public void setResolution(ActionChoice resolution) {
        this.resolution = resolution;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
