package org.zombies;

import java.awt.Rectangle;

import org.game.GamePanel;
import org.plants.PotatoMine;
import org.plants.Squash;

public class Zombie_Brawler extends Zombie {

  boolean isAttacking = false;

  public Zombie_Brawler(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 2, 32, 12);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 4;
    numOfRunning = 4;

    description = "[" + "Brawler"
        + "]\nLike to punch enemies";
    loadAnimations(3, 4, "zombies/Zombie_Brawler.png", 20, 20);
    up1 = animations[0][0];
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    super.update();
  }

  @Override
  public void actionAttack() {
    if ((gp.elapsedTime + timeSpawn) % attack_speed == 0) { // Is this good practice? probably :)
      int plantIndex = 9999;
      for (int i = 0; i < gp.plants.length; i++) {
        if (gp.plants[i] != null) {
          if (worldX - gp.plants[i].worldX <= 1 * gp.tileSize) {
            plantIndex = i;
            break;
          }
        }
      }
      if (plantIndex != 9999) {
        isAttacking = true;
        if (!(gp.plants[plantIndex] instanceof PotatoMine) && !(gp.plants[plantIndex] instanceof Squash)) {
          gp.plants[plantIndex].healthPoint -= damage;
          System.out.println("Zombie Attack");
          if (gp.plants[plantIndex].healthPoint <= 0) {
            gp.plants[plantIndex] = null;
          }
        } else if (gp.plants[plantIndex] instanceof PotatoMine) {
          System.out.println(plantIndex);
          if (((PotatoMine) gp.plants[plantIndex]).isActive) {
            ((PotatoMine) gp.plants[plantIndex]).explode();
            gp.plants[plantIndex] = null;
            System.out.println("Hello");
          } else {
            gp.plants[plantIndex].healthPoint -= damage;
            System.out.println("Zombie Attack");
            if (gp.plants[plantIndex].healthPoint <= 0) {
              gp.plants[plantIndex] = null;
            }
          }
        } else if (gp.plants[plantIndex] instanceof Squash) {
          ((Squash) gp.plants[plantIndex]).actionAttack();
          gp.plants[plantIndex] = null;
        }
      } else {
        isAttacking = false;
      }
    } else {
      isAttacking = false;
    }
  }

  @Override
  protected void setAnimation() {
    if (!collisionOn && (gp.gameState == gp.playState || gp.gameState == gp.sleepState) && !isAttacking) {
      if (!moving) {
        image = animations[0][aniIndex % numOfIdle];
      } else {
        image = animations[1][aniIndex % numOfRunning];
      }
    } else if (isAttacking) {
      image = animations[2][aniIndex % 4];
    }
  }
}
