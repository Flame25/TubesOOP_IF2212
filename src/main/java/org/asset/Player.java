package org.asset;

import org.game.KeyHandler;
import org.game.LoadImage;
import org.game.GamePanel;
import org.object.Object_Bed;
import org.plants.Plants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import static org.game.Constants.PlayerConstants.*;
import static org.game.Constants.*;
import static org.game.Constants.Directions.*;

public class Player extends Entity {
  GamePanel gp;
  KeyHandler keyH;
  public final int screenX;
  public final int screenY;
  public int EggTotal = 0;
  public ArrayList<Plants> inventory = new ArrayList<>();
  public ArrayList<Plants> deck = new ArrayList<>();
  public final int inventorySize = 20;
  private int beforeSleepX;
  private int beforeSleepY;
  private Sun totalSun;

  public Player(GamePanel gp, KeyHandler keyH) {
    super(gp);
    this.gp = gp;
    this.keyH = keyH;
    this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
    totalSun = Sun.getInstance();
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    loadAnimations();
    setDefaultValues();
    setItems();
  }

  public void setDefaultValues() {
    worldX = 10 * gp.tileSize;
    worldY = 6 * gp.tileSize;
    speed = 4;
    direction = "down";
  }

  private void loadAnimations() {
    BufferedImage img = LoadImage.GetSpriteAtlas("player/Player_Walk.png");
    animations = new BufferedImage[8][4];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 16, j * 16, 16, 16);
  }

  public int getSun() {
    return totalSun.getTotalSun();
  }

  public void setSun(int num) {
    totalSun.setTotalSun(num);
  }

  @Override
  public void setAction() {

  }

  public void update() {

    if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
      moving = true;
      if (keyH.downPressed) {
        direction = "down";
      } else if (keyH.upPressed) {
        direction = "up";
      } else if (keyH.leftPressed) {
        direction = "left";
      } else if (keyH.rightPressed) {
        direction = "right";
      }

      // Check Tile Collision
      collisionOn = false;
      gp.cChecker.checkTile(this);
      gp.cChecker.checkEntity(this, gp.plants);

      // Check Object Collision
      int objectIndex = gp.cChecker.checkObject(this, true);
      pickUpItem(objectIndex);
      interactObject(objectIndex);

      // Check NPC Collision
      int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
      interactNPC(npcIndex);

      // if (gp.gameState == gp.sleepState) {
      // for (int i = 0; i < deck.size(); i++) {
      // if (deck.get(i) != null && gp.elapsedTime != deck.get(i).timeSpawn) {
      // if (((gp.elapsedTime - deck.get(i).timeSpawn) % 10) == 0) {
      // deck.get(i).statusOn = true;
      // // System.out.println("Info");
      // } else if (!deck.get(i).statusOn) {
      // deck.get(i).up1 = deck.get(i).down1;
      // }
      // }
      // }
      // }
      updatePost();
    } else {
      moving = false;
    }

    if (gp.gameState != gp.characterState) {
      updateAnimationTick();
      setAnimation();
    }
  }

  public void pickUpItem(int index) {
    if (index != 9999) {
      switch (gp.obj[index].name) {
        case "Egg":
          gp.obj[index] = null;
          EggTotal++;
          gp.ui.showMessage("You got a Eggs");
      }
    }
  }

  public void setItems() {
    for (int i = 0; i < gp.listOfPlants.length; i++) {
      if (gp.listOfPlants[i] != null) {
        inventory.add(gp.listOfPlants[i]);
      }
    }
  }

  @Override
  public void draw(Graphics2D g2) {

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;
    if (gp.gameState == gp.sleepState) {
      try {
        image = ImageIO.read(getClass().getResourceAsStream("/objects/Invisible.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

  }

  public void interactNPC(int i) {
    if (i != 9999) {
      if (gp.keyH.eKeyPressed) {
        gp.gameState = gp.dialogState;
        gp.npc[i].speak();
        gp.keyH.eKeyPressed = false;
      }
    }
  }

  public void interactObject(int i) {
    if (i != 9999) {
      if (gp.keyH.eKeyPressed) {
        if (i == 2 && gp.player.deck.size() == 6) {

          try {
            gp.obj[2].image = ImageIO.read(getClass().getResourceAsStream("/objects/Bed_Sleeping1.png"));
            ((Object_Bed) gp.obj[2]).image_bottom = ImageIO
                .read(getClass().getResourceAsStream("/objects/Bed_Sleeping2.png"));
          } catch (IOException e) {
            e.printStackTrace();
          }
          gp.gameState = gp.sleepState;
          gp.keyH.eKeyPressed = false;
          beforeSleepX = worldX;
          beforeSleepY = worldY;
          worldX = 21 * gp.tileSize;
          worldY = 8 * gp.tileSize;
        }

      }
    }
  }

  public void backToPost() {
    worldY = beforeSleepY;
    worldX = beforeSleepX;
  }

  private void updateAnimationTick() {
    aniTick++;
    if (aniTick >= ANI_SPEED) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= GetSpriteAmount(RUNNING)) {
        aniIndex = 0;
      }
    }
  }

  private void setAnimation() {
    if (!collisionOn && (gp.gameState == gp.playState || gp.gameState == gp.sleepState)) {

      switch (direction) {
        case "up":
          if (!moving) {
            image = animations[UP][0];
          } else {

            image = animations[UP][aniIndex];
          }
          break;
        case "down":
          if (!moving) {
            image = animations[DOWN][0];
          } else {

            image = animations[DOWN][aniIndex];
          }
          break;
        case "left":
          if (!moving) {
            image = animations[LEFT][0];
          } else {

            image = animations[LEFT][aniIndex];
          }
          break;
        case "right":
          if (!moving) {
            image = animations[RIGHT][0];
          } else {

            image = animations[RIGHT][aniIndex];
          }
          break;
      }

    }
  }

  private void resetAniTick() {
    aniTick = 0;
    aniIndex = 0;
  }

  private void updatePost() {

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
}
