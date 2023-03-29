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
    void characterReceives50DamageFromAnotherCharacter() {
        //GIVEN
        Character johnWick = new Character("John Wick");
        Character poorVictim = new Character("Poor victim");
        //WHEN
        johnWick.attacks(poorVictim, 50);
        var sut = poorVictim.getHealth();
        //THEN
        assertEquals(950, sut);
    }

    @Test
    void characterDiesWhenDamageCausesHealthToGoBelowZeroAndHealthCannotGoBelowZero() {
        //GIVEN
        Character kenny = new Character("Kenny");
        //WHEN
        kenny.damage(1001);
        var sut = kenny.isAlive();
        var sut2 = kenny.getHealth();
        //THEN
        assertEquals(false,sut);
        assertEquals(0L,sut2);
    }


    @Test
    void characterCanHealOtherCharactersOnlyIfNotDead() {
        //GIVEN
        Character drHouse = new Character("Dr. House");
        Character curablePatient = new Character("Patient");
        Character deadPatient = new Character("Dead Patient");

        //WHEN
        curablePatient.damage(500); // patient receives 500 damage
        drHouse.heals(curablePatient, 50); // Dr. heals patient;
        deadPatient.damage(1001); // second patient receives 1001 damage and dies
        drHouse.heals(deadPatient, 50); // Dr. tries to heal the second patient, but he's dead already!
        var sut1 = curablePatient.getHealth();
        var sut2 = deadPatient.getHealth();

        //THEN
        assertEquals(550, sut1);
        assertEquals(0,sut2);
    }
}