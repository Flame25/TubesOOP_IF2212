package org.plants;

import org.game.GamePanel;

public class PotatoMine extends Plants {

  public boolean isActive = false;

  public PotatoMine(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
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

  @Override
  public void update() {
    super.update();
    if (isActive) {
      image = down2;
    }
  }

  private void loadImage() {
    up1 = setup("/plants/Chopper", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Chopper", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Chopper_OFF", gp.tileSize, gp.tileSize);
    down2 = setup("/plants/Chopper_Hiding", gp.tileSize, gp.tileSize);
  }

  @Override
  public void actionAttack() {
    System.out.println(timeSpawn);
    if (timeSpawn + 5 == gp.elapsedTime && !isActive) {
      isActive = true;
      System.out.println("Mengumpat");
    }

  }

  public void explode() {
    for (int i = 0; i < gp.zombie.length; i++) {
      if (gp.zombie[i] != null) {

        if (this.worldY + 16 >= gp.zombie[i].worldY + gp.zombie[i].solidArea.y + gp.zombie[i].solidArea.height
            && this.worldY - 32 <= gp.zombie[i].worldY + gp.zombie[i].solidArea.y) {

          if (gp.zombie[i].worldX - (this.worldX + gp.zombie[i].solidArea.x) <= 3 * gp.tileSize) {
            gp.zombie[i] = null;
          }
        }
      }
    }

  }

}
