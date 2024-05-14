package org.asset;

import org.game.KeyHandler;

import org.game.GamePanel;
import org.object.Object_Bed;
import org.plants.Plants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
  private int totalSun = 0;

  public int getTotalSun() {
    return totalSun;
  }

  public Player(GamePanel gp, KeyHandler keyH) {
    super(gp);
    this.gp = gp;
    this.keyH = keyH;
    this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
    totalSun = 0;
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    setDefaultValues();
    getPlayerImage();
    setItems();
  }

  public void setDefaultValues() {
    worldX = 100;
    worldY = 100;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up1.png")));
      up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up2.png")));
      down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png")));
      down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png")));
      left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left1.png")));
      left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png")));
      right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right1.png")));
      right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setAction() {

  }

  public void update() {

    // Check Projectiles Collision ( Will be deleted )
    int projIndex = gp.cChecker.checkProjectile(this);
    actionProjectiles(projIndex);

    if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
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
      }
    }
    if (gp.gameState == gp.playState) {
      spriteCounter++;
      if (spriteCounter > 10) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }
    if (gp.gameState == gp.sleepState) {
      for (int i = 0; i < deck.size(); i++) {
        if (deck.get(i) != null && gp.elapsedTime != deck.get(i).timeSpawn) {
          if (((gp.elapsedTime - deck.get(i).timeSpawn) % 10) == 0) {
            deck.get(i).up1 = deck.get(i).up2;
            deck.get(i).statusOn = true;
            // System.out.println("Info");
          } else if (!deck.get(i).statusOn) {
            deck.get(i).up1 = deck.get(i).down1;
          }
        }
      }
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

  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    if (gp.gameState == gp.playState) {
      switch (direction) {
        case "up":
          if (spriteNum == 1) {
            image = up1;
          }
          if (spriteNum == 2) {
            image = up2;
          }
          break;
        case "down":
          if (spriteNum == 1) {
            image = down1;
          }
          if (spriteNum == 2) {
            image = down2;
          }
          break;
        case "left":
          if (spriteNum == 1) {
            image = left1;
          }
          if (spriteNum == 2) {
            image = left2;
          }
          break;

        case "right":
          if (spriteNum == 1) {
            image = right1;
          }
          if (spriteNum == 2) {
            image = right2;
          }
          break;
      }
    }
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
        if (i == 2) {

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

  private void actionProjectiles(int i) {
    if (i != 9999) {
      System.out.println("Player Hit");
      gp.proj[i] = null;
    }
  }
}
