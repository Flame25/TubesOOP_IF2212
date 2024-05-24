package org.plants;

import org.game.GamePanel;

public class Squash extends Plants {
  public Squash(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    description = "[" + "Squash"
        + "]\nWhen Squash spots a zombie in an \narea near him, he will stomp it for 1800 \narea damage";
    statusOn = true;
    loadImage();
    image = up1;
  }

  @Override
  public void setAction() {

  }

  private void loadImage() {
    up1 = setup("/plants/Squash_2", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Squash_2", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Squash_Off", gp.tileSize, gp.tileSize);
  }

  @Override
  public void actionAttack() {
    for (int i = 0; i < gp.zombie.length; i++) {
      if (gp.zombie[i] != null) {
        if (gp.zombie[i].solidArea.intersects(this.solidArea)) {
          gp.zombie[i] = null;
        }
      }
    }
  }

}
