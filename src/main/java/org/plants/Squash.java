package org.plants;

import org.game.GamePanel;

public class Squash extends Plants {
  public Squash(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, is_aquatic);
    description = "[" + "Squash"
        + "]\nWhen Squash spots a zombie in an \narea near him, he will stomp it for 1800 \narea damage";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/Squash_2", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Squash_2", gp.tileSize, gp.tileSize);
  }

  public void update() {

  }

  @Override
  public void actionAttack() {
  }
}
