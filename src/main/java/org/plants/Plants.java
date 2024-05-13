package org.plants;

import org.asset.Entity;
import org.asset.Action;
import org.game.GamePanel;

public class Plants extends Entity implements Cloneable {
  public int healthPoint;
  public int attack_speed;
  int damage;
  public boolean is_aquatic;
  int range;
  int cooldown;
  int cost;
  public String description;

  // TODO: Plants not counting time based on when the plants are placed
  // TODO: Add Cost into Constructor
  public Plants(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, boolean is_aquatic) {
    super(gp);
    this.range = range;
    this.is_aquatic = is_aquatic;
    this.worldY = 49 * gp.tileSize;
    this.worldX = 49 * gp.tileSize;
    this.healthPoint = healthPoint;
    this.attack_speed = attack_speed;
    this.speed = 0;
    this.damage = damage;
    direction = "up";

  }

  @Override
  public void setAction() {

  }

  public void actionAttack() {

  }

  @Override
  public Plants clone() throws CloneNotSupportedException {
    try {
      return (Plants) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
