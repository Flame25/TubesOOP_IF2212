package org.plants;

import org.game.GamePanel;

public class Sunflower extends Plants {
  public Sunflower(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    this.attack_speed = 3; // Used as delay for generating sun
    description = "[" + "Sunflower"
        + "]\nShe plays a crucial part of the \nplayer's defense, producing extra \nSun to plant more plants.";
    getImage();
  }

  @Override
  public void setAction() {

  }

  private void getImage() {
    up1 = setup("/plants/Sunflower_Better", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Sunflower_Better", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Sunflower_OFF", gp.tileSize, gp.tileSize);
  }

  public void update() {

  }

  @Override
  public void actionAttack() {
    gp.player.setSun(gp.player.getSun() + 25);
  }
}
