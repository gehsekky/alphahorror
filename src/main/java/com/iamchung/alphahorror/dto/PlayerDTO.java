package com.iamchung.alphahorror.dto;

import com.iamchung.alphahorror.model.Player;

public class PlayerDTO {
    private String name;

    private Integer hitPoints;
    private Integer spellPoints;

    private CharacterClassDTO characterClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getSpellPoints() {
        return spellPoints;
    }

    public void setSpellPoints(Integer spellPoints) {
        this.spellPoints = spellPoints;
    }

    public CharacterClassDTO getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClassDTO characterClass) {
        this.characterClass = characterClass;
    }

    public static PlayerDTO from(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(player.getName());
        playerDTO.setCharacterClass(CharacterClassDTO.from(player.getCharacterClass()));
        playerDTO.setHitPoints(player.getHitPoints());
        playerDTO.setSpellPoints(player.getSpellPoints());
        return playerDTO;
    }
}
