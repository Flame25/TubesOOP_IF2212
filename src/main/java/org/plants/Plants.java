package org.plants;

import org.asset.Entity;
import org.asset.Action;
import org.game.GamePanel;

public class Plants extends Entity implements Cloneable {
  public int healthPoint;
  public int attack_speed;
  int damage;
  boolean is_aquatic;
  int range;
  int cooldown;
  int cost;
  public String description;

  public Plants(GamePanel gp, int healthPoint, int attack_speed, int damage) {
    super(gp);
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
      // TODO: copy mutable state here, so the clone can't change the internals of the
      // original
      return (Plants) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
