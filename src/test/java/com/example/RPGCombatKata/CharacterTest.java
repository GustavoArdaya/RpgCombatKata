package com.example.RPGCombatKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void startingCharacterHasHealthOf1000() {
        //GIVEN
        Character conan = new Character("Conan");
        //WHEN
        var sut = conan.getHealth();
        //THEN
        assertEquals(1000L, sut);
    }

    @Test
    void startingCharacterHasLevelOf1() {
        //GIVEN
        Character rambo = new Character("Rambo");
        //WHEN
        var sut = rambo.getLevel();
        //THEN
        assertEquals(1,sut);
    }

    @Test
    void startingCharacterisAlive() {
        //GIVEN
        Character terminator = new Character("Terminator");
        //WHEN
        var sut = terminator.isAlive();
        //THEN
        assertEquals(true,sut);
    }

}