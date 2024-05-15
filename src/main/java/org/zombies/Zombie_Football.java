package org.zombies;

import org.asset.Action;
import org.game.GamePanel;

import java.awt.*;

public class Zombie_Football extends Zombie {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public Zombie_Football(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
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
  // Special Action: Extra Attack Range - Football Zombie get boost in its move speed for about double of its default value for 1 second
  public void setAction() {
    long sleeptime = 1000;
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          setSpeed(2 * getSpeed());
          Thread.sleep(sleeptime);
          setSpeed(Math.floorMod(getSpeed(), 2));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread.start();
  }

  public void getPlayerImage() {
  }
}
