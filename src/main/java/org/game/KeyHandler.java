package org.game;

import org.object.Object_Bed;
import org.plants.Lilypad;

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
      if (code == KeyEvent.VK_V) {
        gp.gameState = gp.zombieAlmnc;
      }
      if (code == KeyEvent.VK_H) {
        gp.gameState = gp.helpState;
      }
    } else if (gp.gameState == gp.helpState) {
      if (code == KeyEvent.VK_H) {
        gp.gameState = gp.playState;
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
            if (gp.player.deck.size() < 6)
              gp.player.deck.add(gp.listOfPlants[gp.ui.getItemIndexOnSlot()]);
          } else {
            gp.player.deck.remove(gp.listOfPlants[gp.ui.getItemIndexOnSlot()]);
          }
        }
      }
    } else if (gp.gameState == gp.zombieAlmnc) {
      if (code == KeyEvent.VK_V) {
        gp.gameState = gp.playState;
      }

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
    }
    // SLEEP STATE
    else if (gp.gameState == gp.sleepState) {
      if (code == KeyEvent.VK_Q) {
        for (int i = 0; i < gp.plants.length; i++) {
          if (gp.plants[i] != null) {
            if (gp.plants[i].worldY == gp.player.worldY - 16 - gp.tileSize
                && gp.plants[i].worldX == gp.player.worldX + 3) {
              gp.plants[i] = null;
              break;
            }
          }
        }
      }
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
      } else if (code == KeyEvent.VK_G) {
        gp.player.setSun(9999);

        // TODO: Refactor Code Below !!
      } else if (gp.player.worldY != 10 * gp.tileSize && gp.player.worldY != 11 * gp.tileSize
          && gp.player.worldY != 14 * gp.tileSize && gp.player.worldY != 15 * gp.tileSize) {
        if (code >= 49 && code <= 57) {

          boolean isAvailable = checkPlantLocation(gp.player.worldX + 3, gp.player.worldY - 16 - gp.tileSize);
          boolean letsPlant = false;
          boolean posisionOnWater = false;
          if (gp.player.worldY == 12 * gp.tileSize || gp.player.worldY == 13 * gp.tileSize) {
            posisionOnWater = true;
          }
          // Water? and not aquatic plants
          if ((gp.player.worldY == 12 * gp.tileSize || gp.player.worldY == 13 * gp.tileSize)
              && !(gp.player.deck.get(code - 49).is_aquatic) && isAvailable) {
            // IF empty check for lilypad
            for (int i = 0; i < gp.plants.length; i++) {
              if (gp.plants[i] != null) {
                if (gp.plants[i] instanceof Lilypad) {

                  // Check Post )
                  if (gp.plants[i].worldX == gp.player.worldX + 3
                      && gp.player.worldY - 16 - gp.tileSize == gp.plants[i].worldY) {
                    letsPlant = true;
                  }
                }
              }
            }
          } else if (gp.player.deck.get(code - 49).is_aquatic
              && checkLilypadLocation(gp.player.worldX + 3, gp.player.worldY - 16 - gp.tileSize)) {
            letsPlant = true;
          } else if (!gp.player.deck.get(code - 49).is_aquatic && isAvailable) {
            letsPlant = true;
          }

          if (letsPlant) {

            System.out.println("Plant " + (code - 48) + " Selected");
            System.out.println(gp.player.deck.get(code - 49).is_aquatic);

            if (gp.player.getSun() >= gp.player.deck.get(code - 49).getCost()) {

              for (int i = 0; i < gp.plants.length; i++) {

                if (((gp.player.worldY == 9 * gp.tileSize || gp.player.worldY == 8 * gp.tileSize)
                    || (gp.player.worldY == 16 * gp.tileSize && gp.player.worldY == 17 * gp.tileSize))
                    && gp.player.deck.get(code - 49).is_aquatic) {
                  break;
                }
                if (gp.plants[i] == null && gp.player.deck.get(code - 49).statusOn) {
                  try {
                    gp.plants[i] = gp.player.deck.get(code - 49).clone();
                    gp.plants[i].timeSpawn = gp.elapsedTime;
                  } catch (CloneNotSupportedException c) {
                    c.printStackTrace();
                  }
                  if (!posisionOnWater) {

                    gp.plants[i].worldX = gp.player.worldX + 3;
                    gp.plants[i].worldY = gp.player.worldY - 16 - gp.tileSize;
                  } else {
                    gp.plants[i].worldX = gp.player.worldX + 3;
                    gp.plants[i].worldY = gp.player.worldY - 16 - gp.tileSize;
                  }

                  System.out.println("Created here : " + gp.plants[i].worldX + ", " + gp.plants[i].worldY);
                  gp.player.deck.get(code - 49).statusOn = false;
                  gp.player.deck.get(code - 49).timeSpawn = gp.elapsedTime;
                  gp.player.setSun(gp.player.getSun() - gp.player.deck.get(code - 49).getCost());
                  break;
                }
              }
            }
          }

        }
      }
    } else if (gp.gameState == gp.endState)

    {
      if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.playState;
        gp.player.backToPost();
        ((Object_Bed) gp.obj[2]).imageToDef();
        gp.elapsedTime = 0;
        gp.player.setSun(100);
        for (int i = 0; i < gp.plants.length; i++) {
          if (gp.plants[i] != null) {
            gp.plants[i] = null;
          }
        }
        for (int i = 0; i < gp.zombie.length; i++) {
          if (gp.plants[i] != null) {
            gp.plants[i] = null;
          }
        }
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
        if (gp.plants[i].worldX == x && gp.plants[i].worldY == y && !(gp.plants[i] instanceof Lilypad)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean checkLilypadLocation(int x, int y) {

    for (int i = 0; i < gp.plants.length; i++) {
      if (gp.plants[i] != null) {
        if (gp.plants[i].worldX == x && gp.plants[i].worldY == y && (gp.plants[i] instanceof Lilypad)) {
          return false;
        }
      }
    }
    return true;
  }

}
