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

    public Boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public void attacks(Character victim, int damage) {
        if (victim != this) {
            victim.damage(damage);
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
