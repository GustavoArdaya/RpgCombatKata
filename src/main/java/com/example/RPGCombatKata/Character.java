package com.example.RPGCombatKata;

public class Character {
    private String name;
    private Long health;
    private Integer level;
    private Boolean isAlive;

    public Character(String name) {
        this.name = name;
        this.health = 1000L;
        this.level = 1;
        this.isAlive = true;
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

    public Boolean getAlive() {
        return this.isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }
}
