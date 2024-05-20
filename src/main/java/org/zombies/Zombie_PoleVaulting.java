package org.zombies;

import org.asset.Action;
import org.game.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Zombie_PoleVaulting extends Zombie implements Action {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public static int actionLeft = 1;

  public Zombie_PoleVaulting(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
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
  // Special Action: Jump - Pole Vaulting Zombie able to jump once with its pole and delete the plant it jumped over
  public void setAction() {
    if (Zombie_PoleVaulting.actionLeft > 0){
      if (gp.elapsedTime % attack_speed == 0) { // Is this good practice? probably :)
        int plantIndex = gp.cChecker.checkEntity(this, gp.plants);
        if (plantIndex != 9999) {
          gp.plants[plantIndex].healthPoint = 0; // Plant "died instantly"
          Zombie_PoleVaulting.actionLeft--; 
          System.out.println("Zombie Jumped Over");
          if (gp.plants[plantIndex].healthPoint <= 0) {
            gp.plants[plantIndex] = null;
          }
        }
      }
    }
  }

  public void getPlayerImage() {
    left1 = setup("/plant_vs_zombie_pixel_image/zomble/struts5", gp.tileSize * 3, gp.tileSize * 3);
    left2 = left1;
  }
}
