package org.zombies;

import org.game.GamePanel;

import java.awt.*;

public class Zombie_BucketHead extends Zombie {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public Zombie_BucketHead(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 2, 32, 12);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 7;
    numOfRunning = 7;
    description = "[" + "Bucket Head"
        + "]\nBucket on my head";
    state = numOfIdle;
    loadAnimations(2, 7, "zombies/Zombie_Buckethead.png", 16, 16);
    up1 = animations[0][0];
  }

}
