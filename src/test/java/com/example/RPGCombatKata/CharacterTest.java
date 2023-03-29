package com.example.RPGCombatKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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

    //Iteration two tests
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

    @Test
    void victimIncreasesDamageCausedBy50PercentIfFiveLevelsBelowCharacter() {

        //GIVEN
        Character johnStrong = new Character("John Strong");
        Character farmingFodder = new Character("Farming Fodder");
        Character evilBrother = new Character("Evil Brother");
        Character highSchoolBully = new Character("High School Bully");

        //WHEN
        johnStrong.setLevel(10);
        farmingFodder.setLevel(4);
        evilBrother.setLevel(5);
        highSchoolBully.setLevel(6);

        johnStrong.attacks(farmingFodder, 100);
        johnStrong.attacks(evilBrother, 100);
        johnStrong.attacks(highSchoolBully, 100);

        var sut1 = farmingFodder.getHealth();
        var sut2 = evilBrother.getHealth();
        var sut3 = highSchoolBully.getHealth();

        //THEN
        assertEquals(850, sut1);
        assertEquals(850,sut2);
        assertEquals(900, sut3);
    }

    //Iteration three tests

    @Test
    void CharacterMustHaveIntegerAttackRange() {
        //GIVEN
        Character thrall = new Character("Thrall");
        Character gimli = new Melee("Gimli");
        Character legolas = new Ranged("Legolas");

        //WHEN
        var sut1 = thrall.getRange() instanceof Integer;
        var sut2 = gimli.getRange() instanceof Integer;
        var sut3 = legolas.getRange() instanceof Integer;

        //THEN
        assertTrue(sut1);
        assertTrue(sut2);
        assertTrue(sut3);

    }

    @Test
    void DefaultCharactersRangeMustChangeAccordingToClass() {
        //GIVEN
        Character gimli = new Melee("Gimli");
        Character legolas = new Ranged("Legolas");

        //WHEN
        var sut1 = gimli.getRange();
        var sut2 = legolas.getRange();

        //THEN
        assertEquals(2, sut1);
        assertEquals(20, sut2);
    }

    @Test
    void EnemyShouldBeInRangeToBeAttacked() {
        //Given
        Character frenchKnight = new Melee("French Knight");
        Character longBowman = new Ranged("LongBowman");

        //WHEN
        frenchKnight.setPosition(8,8);
        longBowman.setPosition(0,0);
        frenchKnight.attacks(longBowman, 100);
        longBowman.attacks(frenchKnight,100);

        var sut1 = frenchKnight.getHealth();
        var sut2 = longBowman.getHealth();

        //THEN
        assertEquals(900, sut1);
        assertEquals(1000, sut2);

    }

    // Iteration four tests

    @Test
    void characterStartsWithoutFactionButCanJoinOneOrMany() {
        //GIVEN

        Character sigmaMaleChad = new Character("Chad");
        Character badTasteBenny = new Character("Bad Taste Benny");
        Character peerPressureJohn = new Character("John");

        Faction jocksClub = new Faction("Jocks Club");
        Faction badBunnyFans = new Faction("Bad Bunny Fan Club");

        //WHEN
        badTasteBenny.joinFaction(badBunnyFans);
        peerPressureJohn.joinFaction(jocksClub, badBunnyFans);

        var sut1 = sigmaMaleChad.getFactions();
        var sut2 = badTasteBenny.getFactions();
        var sut3 = peerPressureJohn.getFactions();

        //THEN

        assertThat(sut1, hasSize(0));
        assertThat(sut2, hasSize(1));
        assertThat(sut3, hasSize(2));
    }

    @Test
    void CharacterMayLeaveOneOrManyFactions() {
        //GIVEN
        Character faithfulFin = new Character("Faithful Fin");
        Character traitorTom = new Character("Traitor Tom");
        Character renegadeRoy = new Character("Renegade Roy");

        Faction heroAlliance = new Faction("Hero Alliance");
        Faction spiesGuild = new Faction("Spies' Guild");

        //WHEN
        faithfulFin.joinFaction(heroAlliance);
        traitorTom.joinFaction(heroAlliance, spiesGuild);
        renegadeRoy.joinFaction(heroAlliance, spiesGuild);

        traitorTom.leaveFaction(heroAlliance);
        renegadeRoy.leaveFaction(heroAlliance, spiesGuild);

        var sut1 = faithfulFin.getFactions();
        var sut2 = traitorTom.getFactions();
        var sut3 = renegadeRoy.getFactions();

        //THEN
        assertThat(sut1, hasSize(1));
        assertThat(sut2, hasSize(1));
        assertThat(sut3, hasSize(0));

    }

    @Test
    void charactersInSameFactionAreAllies() {

        //GIVEN
        Character masterChief = new Character("Master Chief");
        Character cortana = new Character("Cortana");
        Character sangheili = new Character("Sangheili");

        Faction spartan = new Faction("Spartan");
        Faction spaceCommand = new Faction("United Nations Space Command");
        Faction forerunners = new Faction("Forerunners");
        Faction covenant = new Faction("Covenant");

        //WHEN
        masterChief.joinFaction(spartan, spaceCommand);
        cortana.joinFaction(spaceCommand,forerunners);
        sangheili.joinFaction(covenant);

        var sut1 = masterChief.isAlly(cortana);
        var sut2 = cortana.isAlly(masterChief);
        var sut3 = masterChief.isAlly(sangheili);

        //THEN

        assertTrue(sut1);
        assertTrue(sut2);
        assertFalse(sut3);
    }

    @Test
    void characterCannotDamageAllies() {
        //GIVEN
        Character masterChief = new Character("Master Chief");
        Character cortana = new Character("Cortana");
        Character sangheili = new Character("Sangheili");

        Faction spartan = new Faction("Spartan");
        Faction spaceCommand = new Faction("United Nations Space Command");
        Faction forerunners = new Faction("Forerunners");
        Faction covenant = new Faction("Covenant");

        //WHEN
        masterChief.joinFaction(spartan, spaceCommand);
        cortana.joinFaction(spaceCommand,forerunners);
        sangheili.joinFaction(covenant);

        masterChief.attacks(cortana, 50);
        masterChief.attacks(sangheili, 50);

        var sut1 = cortana.getHealth();
        var sut2 = sangheili.getHealth();

        //THEN

        assertEquals(1000, sut1);
        assertEquals(950, sut2);
    }
}