package org.zombies;

import java.awt.Rectangle;

import org.game.GamePanel;

public class Zombie_Slime extends Zombie {

  boolean isFull = false;

  public Zombie_Slime(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 2, 32, 12);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 7;
    numOfRunning = 4;
    moving = false;
    state = numOfIdle;

    loadAnimations(2, 7, "zombies/Robot.png", 16, 16);
  }

  @Override
  protected void setAnimation() {
    if (!collisionOn && (gp.gameState == gp.playState || gp.gameState == gp.sleepState)) {
      if (!moving && !isFull) {
        image = animations[0][aniIndex];
        if (aniIndex == 6) {
          isFull = true;
        }
      } else if (isFull && !moving) {
        image = animations[0][6];
      } else {
        isFull = false;
        image = animations[1][aniIndex];
      }
    }
  }
}
