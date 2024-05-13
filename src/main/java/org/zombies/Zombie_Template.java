package org.zombies;

import org.game.GamePanel;

import java.awt.*;

public class Zombie_Template extends Zombie {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public Zombie_Template(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed) {
    super(gp, healthPoint, speed, damage, attack_speed, false);
    direction = "left";
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    getPlayerImage();
  }

  @Override
  public void update() {
    if (counter >= 60) {
      speed = 3;
      counter = 0;
    }
    super.update();
    this.speed = 0;
    counter++;
  }

  @Override
  public void setAction() {
  }

  public void getPlayerImage() {
  }
}
