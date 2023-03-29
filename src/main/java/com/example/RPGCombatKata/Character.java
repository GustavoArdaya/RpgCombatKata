package com.example.RPGCombatKata;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Character {
    private String name;
    private Long health;
    private Integer level;
    private Boolean isAlive;
    private Integer range;
    List<Integer> position = Arrays.asList(new Integer[2]);

    public Character(String name) {
        this.name = name;
        this.health = 1000L;
        this.level = 1;
        this.isAlive = true;
        this.range = 1;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return this.health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = Arrays.asList(x, y);
    }

    public void attacks(Character victim, int damage) {
        Integer playerPosition = this.getPosition().stream().reduce(0, Integer::sum);
        Integer victimPosition = victim.getPosition().stream().reduce(0, Integer::sum);
        int distance = Math.abs(playerPosition - victimPosition);
        if (victim != this && distance <= this.getRange()) {
            int actualDamage = damage;
            if (victim.getLevel() >= (this.level + 5)) {
                actualDamage = (int)(damage * 0.5);
            } else if (victim.getLevel() <= (this.level - 5)) {
                actualDamage = (int)(damage * 1.5);
            }
            victim.damage(actualDamage);
        }
    }

    public void damage(int damage) {
        this.health -= damage;
        if(this.health <= 0) {
            this.isAlive = false;
            this.setHealth(0L);
        }
    }

    public void heals(Character character, int healthAmount) {
        if (character == this) {
            character.restoreHealth(healthAmount);
        }
    }

    private void restoreHealth(int healthAmount) {
        if(this.isAlive && this.health < 1000) {
            this.health += healthAmount;
            if (this.health > 1000) {
                this.health = 1000L;
            }
        }
    }
}
