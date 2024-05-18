package org.plants;

import org.game.GamePanel;
import org.projectiles.Snowpea_Peas;

public class Snowpea extends Plants {

  public Snowpea(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    description = "[" + "Snowpea" + "]\nPeashooter with snow and slow";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/SnowPea", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/SnowPea", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Snowpea_Off", gp.tileSize, gp.tileSize);

  }

  public void update() {

  }

  @Override
  public void actionAttack() {
    for (int i = 0; i < gp.proj.length; i++) {
      if (gp.proj[i] == null) {
        gp.proj[i] = new Snowpea_Peas();
        gp.proj[i].worldX = this.worldX + gp.tileSize;
        gp.proj[i].worldY = this.worldY - 16;
        break;
      }
    }
  }
}
