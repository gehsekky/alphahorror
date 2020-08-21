package com.iamchung.alphahorror.repository;

import com.iamchung.alphahorror.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

}
