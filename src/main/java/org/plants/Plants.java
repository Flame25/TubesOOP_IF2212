package org.plants;

import java.awt.Graphics2D;

import org.asset.Entity;
import org.game.GamePanel;

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
    this.collision = true;
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

  @Override
  public void update() {
    if (gp.elapsedTime == timeSpawn + cooldown) {
      statusOn = true;
    }
    if (!statusOn) {
      image = down1;
    } else {
      image = up1;
    }
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

  public boolean checkRange() {
    for (int i = 0; i < gp.zombie.length; i++) {
      if (gp.zombie[i] != null) {
        System.out.println("Zombies : " + (gp.zombie[i].solidArea.y + gp.zombie[i].worldY) + ", "
            + (gp.zombie[i].worldY + gp.zombie[i].solidArea.y + gp.zombie[i].solidArea.height));
        if (this.worldY + 16 >= gp.zombie[i].worldY + gp.zombie[i].solidArea.y + gp.zombie[i].solidArea.height
            && this.worldY - 32 <= gp.zombie[i].worldY + gp.zombie[i].solidArea.y) {
          if (gp.zombie[i].worldX - (this.worldX + gp.zombie[i].solidArea.x) <= range * gp.tileSize) {
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
