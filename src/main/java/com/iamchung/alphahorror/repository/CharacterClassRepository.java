package com.iamchung.alphahorror.repository;

import com.iamchung.alphahorror.model.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, UUID> {
    CharacterClass findByName(String name);
}
