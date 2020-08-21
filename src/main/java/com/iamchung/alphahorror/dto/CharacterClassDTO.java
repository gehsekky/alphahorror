package com.iamchung.alphahorror.dto;

import com.iamchung.alphahorror.model.CharacterClass;

public class CharacterClassDTO {

    public String name;
    private Integer strength;
    private Integer constitution;
    private Integer dexterity;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public static CharacterClassDTO from(CharacterClass characterClass) {
        CharacterClassDTO characterClassDTO = new CharacterClassDTO();
        characterClassDTO.setName(characterClass.getName());
        characterClassDTO.setStrength(characterClass.getStrength());
        characterClassDTO.setConstitution(characterClass.getConstitution());
        characterClassDTO.setDexterity(characterClass.getDexterity());
        characterClassDTO.setIntelligence(characterClass.getIntelligence());
        characterClassDTO.setWisdom(characterClass.getWisdom());
        characterClassDTO.setCharisma(characterClass.getCharisma());
        return characterClassDTO;
    }
}
