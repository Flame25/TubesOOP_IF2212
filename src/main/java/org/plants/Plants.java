package org.plants;

import java.awt.Graphics2D;

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

  }

  @Override
  public Plants clone() throws CloneNotSupportedException {
    try {
      isClone = true;
      return (Plants) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  private void actionProjectiles(int i) {
    if (i != 9999) {
      healthPoint -= 200;
      System.out.println(this.getClass().getSimpleName() + " Hat Balled!");
      
      gp.proj[i] = null;
      if (healthPoint <= 0) {
        
        for (int j = 0; j < gp.plants.length; j++) {
          if (gp.plants[j] != null) {
            if (gp.plants[j].equals(this)) {
              gp.plants[j] = null;
            }
          }
        }
      }
    }
  }

  public boolean checkRange() {
    for (int i = 0; i < gp.zombie.length; i++) {
      if (gp.zombie[i] != null) {
        if (gp.zombie[i].worldY == this.worldY) {
          if (gp.zombie[i].worldX - this.worldX <= range * gp.tileSize) {
            return true;
          } else if (range == -1) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void draw(Graphics2D g2) {
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;
    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }
}
