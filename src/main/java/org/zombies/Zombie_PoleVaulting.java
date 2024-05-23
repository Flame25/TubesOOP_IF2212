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
  private int specialSkillUsage = 1;
  private int jumpState = 0;
  private long lastJump = 0;
  private int beforeJumpY = 0;
  private int beforeJumpX = 0;
  private boolean stopJump = false;

  private int tempIndex = 9999;

  public Zombie_PoleVaulting(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 2, 32, 12);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 4;
    numOfRunning = 4;
    specialSkillUsage = 1;
    loadAnimations(3, 4, "zombies/Zombie_Jump.png", 20, 20);
  }

  @Override
  public void update() {
    if (jumpState != 0) {
      if (jumpState == 4) {
        jumpState = 0;
        image = animations[2][jumpState];
        this.worldY = beforeJumpY;
        if (tempIndex != 9999) {
          gp.plants[tempIndex] = null;
        }
      } else if ((lastJump + 150000000) % 1000000000 <= gp.timer) {
        lastJump = gp.timer;
        image = animations[2][jumpState];
        this.worldX -= gp.tileSize;
        worldY -= gp.tileSize;
        jumpState++;
      }
    } else {
      if (counter >= 55) {
        speed = 20;
        counter = 0;
      }
      super.update();
      this.speed = 0;
      counter++;
    }
  }

  @Override
  public void setAction() {
    if (specialSkillUsage == 1) {
      for (int i = 0; i < gp.plants.length; i++) {
        if (gp.plants[i] != null) {
          System.out.println(this.worldX - gp.plants[i].worldX);
          System.out.println(this.worldY - gp.plants[i].worldY);
          if (gp.plants[i].worldY == this.worldY && this.worldX - gp.plants[i].worldX <= gp.tileSize * 2) {
            tempIndex = i;
          }
        }
      }
      if (tempIndex != 9999) {
        System.out.println("Time to jump");
        lastJump = gp.timer;
        image = animations[2][jumpState];
        jumpState++;
        specialSkillUsage--;
        beforeJumpX = worldX;
        beforeJumpY = worldY;
      }
    } else if (gp.elapsedTime % attack_speed == 0 && specialSkillUsage < 1) { // Is this good practice? probably :)
      int plantIndex = gp.cChecker.checkEntity(this, gp.plants);
      if (plantIndex != 9999) {
        gp.plants[plantIndex].healthPoint -= this.damage;
        if (gp.plants[plantIndex].healthPoint <= 0) {
          gp.plants[plantIndex] = null;
        }
        this.worldX -= gp.tileSize;
      }
    }
  }

}
