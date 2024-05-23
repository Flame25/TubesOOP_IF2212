package org.zombies;

import org.asset.Entity;
import org.game.GamePanel;
import org.game.LoadImage;
import org.plants.Chopper;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.game.Constants.PlayerConstants.*;
import static org.game.Constants.*;
import static org.game.Constants.Directions.*;

public class Zombie extends Entity implements Cloneable {
  int healthPoint;
  boolean isAquatic;
  public int attack_speed;
  long countTime = 0; // 60 fps, 1 time walk for each repetition
  int counter = 0;
  long timeNotes = 0;
  int howManySecs = 0;
  int damage = 0;
  int defaultSpeed;
  String statusEffect;
  int attack_range;
  int cost;
  int numOfIdle = 9999;
  int numOfRunning = 9999;

  public Zombie(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) { // TODO : ADD MORE ATTRIBUTES
    super(gp);
    this.attack_range = attack_range;
    this.direction = "left";
    this.speed = speed;
    this.healthPoint = healthPoint;
    this.damage = damage;
    this.attack_speed = attack_speed;
    this.defaultSpeed = 3;
    this.statusEffect = "None";
    this.isAquatic = isAquatic;
    this.counter = 0;
  }

  public void update() {
    collisionOn = false;
    gp.cChecker.checkTile(this);
    gp.cChecker.checkPlayer(this);
    gp.cChecker.checkObject(this, true);

    int projIndex = gp.cChecker.checkProjectile(this);
    actionProjectiles(projIndex);
    if (statusEffect.equals("Slowed")) {
      if (howManySecs >= 3) {
        statusEffect = "None";
        defaultSpeed = 3;
        // left1 = up1;
        // left2 = up2;
      } else {
        // left1 = down1;
        // left2 = down2;
        defaultSpeed = 1;
      }
    }
    gp.cChecker.checkEntity(this, gp.plants);
    if (!collisionOn && gp.elapsedTime >= countTime + 10 && counter <= 48) {
      worldX -= 1;
      moving = true;
      timeNotes = gp.elapsedTime;
      counter++;
    }
    if (counter >= 48) {
      countTime = timeNotes;
      moving = false;
      counter = 0;
    }
    setAnimation();
    updateAnimationTick();
  }

  @Override
  public void setAction() {
    actionAttack();
  }

  public void actionAttack() {
    if (gp.elapsedTime % attack_speed == 0) { // Is this good practice? probably :)
      int plantIndex = gp.cChecker.checkEntity(this, gp.plants);
      if (plantIndex != 9999) {
        if (!(gp.plants[plantIndex] instanceof Chopper)) {
          gp.plants[plantIndex].healthPoint -= damage;
          System.out.println("Zombie Attack");
          if (gp.plants[plantIndex].healthPoint <= 0) {
            gp.plants[plantIndex] = null;
          }
        } else {

          System.out.println(plantIndex);
          if (((Chopper) gp.plants[plantIndex]).isActive) {
            ((Chopper) gp.plants[plantIndex]).explode();
            gp.plants[plantIndex] = null;
            System.out.println("Hello");
          } else {
            gp.plants[plantIndex].healthPoint -= damage;
            System.out.println("Zombie Attack");
            if (gp.plants[plantIndex].healthPoint <= 0) {
              gp.plants[plantIndex] = null;
            }
          }
        }
      }
    }
  }

  private void actionProjectiles(int i) {
    if (i != 9999) {
      healthPoint -= 25;
      System.out.println("Zombie Hit");
      if (gp.proj[i].name.equals("Snowpea Peas")) {
        this.howManySecs = 0;
        this.statusEffect = "Slowed";
        System.out.println("Zombie Slowed");
      }
      gp.proj[i] = null;
      if (healthPoint <= 0) {
        System.out.println("Zombie dead");
        for (int j = 0; j < gp.zombie.length; j++) {
          if (gp.zombie[j] != null) {
            if (gp.zombie[j].equals(this)) {
              gp.zombie[j] = null;
            }
          }
        }
      }
    }
  }

  @Override
  public Zombie clone() throws CloneNotSupportedException {
    try {
      return (Zombie) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public void draw(Graphics2D g2) {

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }

  protected void updateAnimationTick() {
    aniTick++;
    if (aniTick >= ANI_SPEED) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= GetSpriteAmount(RUNNING)) {
        aniIndex = 0;
      }
    }
  }

  protected void setAnimation() {
    if (!collisionOn && (gp.gameState == gp.playState || gp.gameState == gp.sleepState)) {
      if (!moving) {
        image = animations[0][aniIndex % numOfIdle];
      } else {

        image = animations[1][aniIndex % numOfRunning];
      }
    }
  }

  protected void updatePost() {

    // If Collision is False, player can move
    if (!collisionOn && gp.gameState == gp.playState) {
      switch (direction) {
        case "down":
          worldY += speed;
          break;
        case "up":
          worldY -= speed;
          break;
        case "left":
          worldX -= speed;
          break;
        case "right":
          worldX += speed;
          break;
      }
    } else if (collisionOn) {
      moving = false;
    }
  }

  protected void loadAnimations(int rowAnimations, int colAnimations, String path, int width, int height) {
    BufferedImage img = LoadImage.GetSpriteAtlas(path);
    animations = new BufferedImage[rowAnimations][colAnimations];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * width, j * height, width, height);
  }
}
