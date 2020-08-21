package com.iamchung.alphahorror.model.actionChoices;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class ActionChoice implements Serializable {
    protected String name;
    protected String description;
    protected String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
