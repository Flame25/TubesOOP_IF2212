package org.game;

import org.object.Object_Bed;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
  GamePanel gp;
  public boolean upPressed, downPressed, leftPressed, rightPressed, eKeyPressed;

  @Override
  public void keyTyped(KeyEvent e) {
  }

  public KeyHandler(GamePanel gp) {
    this.gp = gp;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    // PLAY STATE
    if (gp.gameState == gp.playState) {
      if (code == KeyEvent.VK_W) {
        upPressed = true;
      }
      if (code == KeyEvent.VK_S) {
        downPressed = true;
      }
      if (code == KeyEvent.VK_A) {
        leftPressed = true;
      }
      if (code == KeyEvent.VK_D) {
        rightPressed = true;
      }
      if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.pauseState;
      }
      if (code == KeyEvent.VK_E) {
        eKeyPressed = true;
      }
      if (code == KeyEvent.VK_C) {
        gp.gameState = gp.characterState;
      }
    }

    // PAUSE STATE
    else if (gp.gameState == gp.pauseState) {

      if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.playState;
      }
    }

    // DIALOGUE STATE
    else if (gp.gameState == gp.dialogState) {
      if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.playState;
      }
    }

    // CHARACTER STATE
    else if (gp.gameState == gp.characterState) {
      if (code == KeyEvent.VK_C) {
        gp.gameState = gp.playState;
      }
      if (code == KeyEvent.VK_W) {
        if (gp.ui.slotRow != 0) {
          gp.ui.slotRow--;
        }
      }
      if (code == KeyEvent.VK_A) {
        if (gp.ui.slotCol != 0) {
          gp.ui.slotCol--;
        }
      }
      if (code == KeyEvent.VK_D) {
        if (gp.ui.slotCol != 4) {
          gp.ui.slotCol++;
        }
      }
      if (code == KeyEvent.VK_S) {
        if (gp.ui.slotRow != 3) {
          gp.ui.slotRow++;
        }
      }
      if (code == KeyEvent.VK_E) {
        if (gp.listOfPlants[gp.ui.getItemIndexOnSlot()] != null) {
          if (!gp.player.deck.contains(gp.listOfPlants[gp.ui.getItemIndexOnSlot()])) {
            gp.player.deck.add(gp.listOfPlants[gp.ui.getItemIndexOnSlot()]);
          } else {
            gp.player.deck.remove(gp.listOfPlants[gp.ui.getItemIndexOnSlot()]);
          }
        }
      }
    }
    // SLEEP STATE
    else if (gp.gameState == gp.sleepState) {
      if (code == KeyEvent.VK_W) {
        if (gp.player.worldY != 8 * gp.tileSize) {
          gp.player.worldY -= gp.tileSize;
        }
      } else if (code == KeyEvent.VK_S) {
        if (gp.player.worldY != 17 * gp.tileSize) {
          gp.player.worldY += gp.tileSize;
        }
      } else if (code == KeyEvent.VK_A) {
        if (gp.player.worldX != 21 * gp.tileSize) {
          gp.player.worldX -= gp.tileSize;
        }
      } else if (code == KeyEvent.VK_D) {
        if (gp.player.worldX != 29 * gp.tileSize) {
          gp.player.worldX += gp.tileSize;
        }
      } else if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.pauseState;

        // TODO: Refactor Code Below !!
      } else if (gp.player.worldY != 10 * gp.tileSize && gp.player.worldY != 11 * gp.tileSize
          && gp.player.worldY != 14 * gp.tileSize && gp.player.worldY != 15 * gp.tileSize) {
        if (code >= 49 && code <= 57) {
          if (((gp.player.worldY == 12 * gp.tileSize || gp.player.worldY == 13 * gp.tileSize)
              && gp.player.deck.get(code - 49).is_aquatic)
              || (gp.player.worldY != 12 * gp.tileSize && gp.player.worldY != 13 * gp.tileSize)
                  && checkPlantLocation(gp.player.worldX + 3, gp.player.worldY - 16 - gp.tileSize)) {

            System.out.println("Plant " + (code - 48) + " Selected");
            System.out.println(gp.player.deck.get(code - 49).is_aquatic);

            if (gp.player.getSun() >= gp.player.deck.get(code - 49).getCost()) {

              for (int i = 0; i < gp.plants.length; i++) {
                if (gp.plants[i] == null && gp.player.deck.get(code - 49).statusOn) {
                  try {
                    gp.plants[i] = gp.player.deck.get(code - 49).clone();
                  } catch (CloneNotSupportedException c) {
                    c.printStackTrace();
                  }
                  gp.plants[i].worldX = gp.player.worldX + 3;
                  gp.plants[i].worldY = gp.player.worldY - 16 - gp.tileSize;
                  gp.player.deck.get(code - 49).statusOn = false;
                  gp.player.deck.get(code - 49).timeSpawn = gp.elapsedTime;
                  System.out.println(gp.player.deck.get(code - 49).timeSpawn);
                  gp.player.setSun(gp.player.getSun() - gp.player.deck.get(code - 49).getCost());
                  break;
                }
              }
            }
          }
        }
      }
      if (code == KeyEvent.VK_E) {
        gp.gameState = gp.playState;
        gp.player.backToPost();
        ((Object_Bed) gp.obj[2]).imageToDef();
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

    int code = e.getKeyCode();
    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }
  }

  public boolean checkPlantLocation(int x, int y) {
    for (int i = 0; i < gp.plants.length; i++) {
      if (gp.plants[i] != null) {
        if (gp.plants[i].worldX == x && gp.plants[i].worldY == y) {
          return false;
        }
      }
    }
    return true;
  }

}
