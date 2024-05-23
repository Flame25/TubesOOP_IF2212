package org.plants;

import org.game.GamePanel;

public class YellowMushroom extends Plants {

  public YellowMushroom(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    this.attack_speed = 2; // Used as delay for generating sun
    description = "[" + "Yellow Mushroom"
        + "]\nShe plays a crucial part of the \nplayer's defense, producing extra \nSun to plant more plants.";
    statusOn = true;
    loadImage();
    image = up1;
  }

  @Override
  public void setAction() {

  }

  private void loadImage() {
    up1 = setup("/plants/Yellow_Mushroom", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Yellow_Mushroom", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Yellow_Mush_Off", gp.tileSize, gp.tileSize);
  }

  @Override
  public void actionAttack() {
    gp.player.setSun(gp.player.getSun() + 5);
  }
}
