package org.plants;

import org.game.GamePanel;

public class Sunflower extends Plants {
  public Sunflower(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, is_aquatic);
    description = "[" + "Sunflower"
        + "]\nShe plays a crucial part of the player's defense, producing extra Sun to plant more plants.";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/Sunflower_Better", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Sunflower_Better", gp.tileSize, gp.tileSize);
  }

  public void update() {

  }

  @Override
  public void actionAttack() {
  }
}
