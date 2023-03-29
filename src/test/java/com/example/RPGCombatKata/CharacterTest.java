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
    void characterCanBeHealedOnlyIfNotDeadAndHealingCannotGoBeyond1000() {
        //GIVEN
        Character deadGuy = new Character("Dead Guy");
        Character healableGuy = new Character("Healable Guy");
        Character wayTooHealthyGuy = new Character("Way Too Healthy Guy");



        //WHEN
        deadGuy.damage(1001);
        healableGuy.damage(500);
        wayTooHealthyGuy.damage(0);
        deadGuy.heals(deadGuy, 50);
        healableGuy.heals(healableGuy, 50);
        wayTooHealthyGuy.heals(wayTooHealthyGuy, 50);

        var sut1 = deadGuy.getHealth();
        var sut2 = healableGuy.getHealth();
        var sut3 = wayTooHealthyGuy.getHealth();

        //THEN
        assertEquals(0, sut1);
        assertEquals(550,sut2);
        assertEquals(1000, sut3);
    }

    @Test
    void characterCannotAttackHimself() {
        //GIVEN
        Character dishonoredSamurai = new Character("Dishonored Samurai");
        //WHEN
        dishonoredSamurai.attacks(dishonoredSamurai, 50);
        var sut = dishonoredSamurai.getHealth();
        //THEN
        assertEquals(1000, sut);
    }

    @Test
    void characterCanOnlyHealItself() {
        //GIVEN
        Character drHouse = new Character("Dr. House");
        Character patient = new Character("Patient");

        //WHEN
        patient.damage(100);
        drHouse.heals(patient, 50);
        drHouse.damage(100);
        drHouse.heals(drHouse, 50);
        var sut1 = patient.getHealth();
        var sut2 = drHouse.getHealth();

        //THEN
        assertEquals(900, sut1);
        assertEquals(950, sut2);
    }

    @Test
    void victimReducesDamageCausedBy50PercentIfFiveLevelsAboveCharacter() {
        //GIVEN
        Character meekPlayer = new Character("Meek Player");
        Character lowlyMinion = new Character("Lowly Minion");
        Character middleMinion = new Character("Middle Minion");
        Character strongMinion = new Character("Final Boss");

        //WHEN
        lowlyMinion.setLevel(5);
        middleMinion.setLevel(6);
        strongMinion.setLevel(7);
        meekPlayer.attacks(lowlyMinion, 100);
        meekPlayer.attacks(middleMinion,100);
        meekPlayer.attacks(strongMinion, 100);

        var sut1 = lowlyMinion.getHealth();
        var sut2 = middleMinion.getHealth();
        var sut3 = strongMinion.getHealth();

        //THEN
        assertEquals(900, sut1);
        assertEquals(950, sut2);
        assertEquals(950, sut3);
    }
}