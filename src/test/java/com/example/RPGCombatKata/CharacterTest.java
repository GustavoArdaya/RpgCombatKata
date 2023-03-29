package com.example.RPGCombatKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @BeforeEach
    void setUp() {
    }

    // Iteracion 1 - 1
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

    //Iteracion 1 - 2

    @Test
    void characterDeals50DamageToOtherCharacter() {
        //GIVEN
        Character johnWick = new Character("John Wick");
        Character poorVictim = new Character("Poor victim");
        //WHEN
        johnWick.attack(poorVictim, 50);
        var sut = poorVictim.getHealth();
        //THEN
        assertEquals(950, sut);
    }

    @Test
    void characterDiesWhenDamageCausesHealthToGoBelowZero() {
        //GIVEN
        Character kenny = new Character("Kenny");
        //WHEN
        kenny.damage(1001);
        var sut = kenny.isAlive();
        //THEN
        assertEquals(false,sut);
    }

}