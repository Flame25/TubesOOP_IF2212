package org.plants;

import org.game.GamePanel;
import org.projectiles.Purple_Projectiles;

public class PurpleMushroom extends Plants {

  public PurpleMushroom(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {

    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    direction = "up";
    description = "[" + "Purple Mushroom" + "]\nNormal and basic peas shooter";
    statusOn = true;
    loadImage();

  }

  private void loadImage() {
    up1 = setup("/plants/Purple_Mushroom", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Purple_Mushroom", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Purple_Mushroom_Off", gp.tileSize, gp.tileSize);
  }

  @Override
  public void actionAttack() {
    System.out.println(gp.zombie[0].solidArea.y - this.worldY);
    if (checkRange()) {
      for (int i = 0; i < gp.proj.length; i++) {
        if (gp.proj[i] == null) {
          gp.proj[i] = new Purple_Projectiles();
          gp.proj[i].worldX = this.worldX + gp.tileSize;
          gp.proj[i].worldY = this.worldY - 16;
          break;
        }
      }
    }
  }

}
