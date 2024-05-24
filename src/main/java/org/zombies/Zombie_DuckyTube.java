package org.zombies;

import org.game.GamePanel;

import java.awt.*;

public class Zombie_DuckyTube extends Zombie {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */

  boolean isSwimming = false;

  public Zombie_DuckyTube(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 4;
    numOfRunning = 4;
    state = numOfIdle;
    description = "[" + "Ducky Tube"
        + "]\nI can swim";
    loadAnimations(4, 4, "zombies/Zombie_Swim.png", 16, 16);
    up1 = animations[0][0];
  }

  @Override
  public void update() {
    super.update();
    if (worldX >= 21 * gp.tileSize && worldX <= 29 * gp.tileSize) {
      isSwimming = true;
    }
  }

  @Override
  protected void setAnimation() {
    if (!collisionOn && (gp.gameState == gp.playState || gp.gameState == gp.sleepState)) {
      if (!isSwimming) {

        if (!moving) {
          image = animations[2][aniIndex % numOfRunning];
        } else {
          image = animations[1][aniIndex % numOfRunning];
        }
      } else {

        if (!moving) {
          image = animations[3][aniIndex % numOfRunning];
        } else {
          image = animations[0][aniIndex % numOfRunning];
        }
      }
    }
  }
}
