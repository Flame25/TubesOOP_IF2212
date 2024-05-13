package org.plants;

import org.game.GamePanel;
import org.projectiles.Peashooter_Peas;

public class Peashooter extends Plants {
  public Peashooter(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost,
      boolean is_aquatic) {

    super(gp, healthPoint, attack_speed, range, damage, cost, is_aquatic);
    direction = "up";
    description = "[" + "Peashooter" + "]\nNormal and basic peas shooter";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/Peashooter", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Peashooter", gp.tileSize, gp.tileSize);
  }

  public void update() {
  }

  @Override
  public void actionAttack() {
    for (int i = 0; i < gp.proj.length; i++) {
      if (gp.proj[i] == null) {
        gp.proj[i] = new Peashooter_Peas();
        gp.proj[i].worldX = this.worldX + gp.tileSize;
        gp.proj[i].worldY = this.worldY - 16;
        break;
      }
    }
  }

}
