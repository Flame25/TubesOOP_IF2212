package org.plants;

import org.asset.Entity;
import org.game.GamePanel;
import org.zombies.Zombie;

public class Plants extends Entity implements Cloneable {
  public int healthPoint;
  public int attack_speed;
  int damage;
  public boolean is_aquatic;
  int range;
  public int cooldown;
  int cost;
  public boolean statusOn = true;
  public String description;
  public long timeSpawn = 9999;
  protected boolean isClone = false;

  // TODO: Plants not counting time based on when the plants are placed
  public Plants(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp);
    this.range = range;
    statusOn = true;
    this.cost = cost;
    this.is_aquatic = is_aquatic;
    this.worldY = 49 * gp.tileSize;
    this.worldX = 49 * gp.tileSize;
    this.healthPoint = healthPoint;
    this.attack_speed = attack_speed;
    this.speed = 0;
    this.damage = damage;
    this.cooldown = cooldown;
    direction = "up";
  }

  // SETTER
  // GETTER
  public int getCost() {
    return cost;
  }

  // METHODS
  @Override
  public void setAction() {

  }

  public void actionAttack() {

  }

  
  public void update() {
    super.update();
    gp.cChecker.checkObject(this, true);
    
    int projIndex = gp.cChecker.checkProjectile(this);
    actionProjectiles(projIndex);
    
  }

  
  public Plants clone() throws CloneNotSupportedException {
    try {
      isClone = true;
      return (Plants) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
