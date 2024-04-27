package org.asset;

public class Plant {
    private String name;
    private int cost;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private int range;
    private int cooldown;

    public Plant(String name, int cost, int health, int attack_damage, int attack_speed, int cooldown)
    {
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.cooldown = cooldown;
    }

    public void setAttack_damage(int attack_damage) {
        this.attack_damage = attack_damage;
    }

    public void setAttack_speed(int attack_speed) {
        this.attack_speed = attack_speed;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    // TODO : Add more setter and getter
}
