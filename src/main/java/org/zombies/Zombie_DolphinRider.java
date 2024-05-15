package org.zombies;

import org.asset.Action;
import org.game.GamePanel;

import java.awt.*;

public class Zombie_DolphinRider extends Zombie implements Action {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public static int actionAttempted = 0;
  
  public Zombie_DolphinRider(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
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
  // Special Action: Water Jump - Dolphin Rider Zombie able to jump once with its dolphin pet and delete the plant it jumped over
  public void setAction() {
    if (Zombie_DolphinRider.actionAttempted == 0){
      if (gp.elapsedTime % attack_speed == 0) { // Is this good practice? probably :)
        int plantIndex = gp.cChecker.checkEntity(this, gp.plants);
        if (plantIndex != 9999) {
          gp.plants[plantIndex].healthPoint = 0; // Plant "died instantly" 
          System.out.println("Zombie Jumped Over");
          if (gp.plants[plantIndex].healthPoint <= 0) {
            gp.plants[plantIndex] = null;
          }
          Zombie_DolphinRider.actionAttempted++;
        }
      }
    }
  }

  public void getPlayerImage() {
  }
}
