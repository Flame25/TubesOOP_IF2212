package org.plants;

import org.game.GamePanel;

public class Wallnut extends Plants {

  public Wallnut(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    collision = true;

    description = "[" + "Wallnut"
        + "\nThey're defensive plants that can't attack \nback, but instead block off \nzombies with their huge \namounts of health.";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/Better_Wallnut", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Better_Wallnut", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Wallnut_Off", gp.tileSize, gp.tileSize);
  }

  public void update() {

  }

  @Override
  public void actionAttack() {
  }
}
