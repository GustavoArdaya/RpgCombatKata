package com.example.RPGCombatKata;

public class Thing {
    private String name;
    private Long health;

    private Boolean isDestroyed;

    public Thing(String name, Long health) {
        this.name = name;
        this.health = health;
        this.isDestroyed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Boolean isDestroyed() {
        return isDestroyed;
    }

    public void receivesDamage( int damage ) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0L;
            this.isDestroyed = true;
        }
    }
}
