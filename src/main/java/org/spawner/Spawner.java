package org.spawner;

import java.util.Random;

import org.asset.Action;
import org.game.GamePanel;
import org.zombies.Zombie;
import org.zombies.Zombie_Giant;

public class Spawner {
  private GamePanel gp;
  private long lastSec = 99999;
  private int numberOfZombie = 20;
  private int spawnedZombie = 0;

  public Spawner(GamePanel gp) {
    this.gp = gp;
    lastSec = gp.elapsedTime;
    Random random = new Random();
    numberOfZombie = random.nextInt(0, 41);
    spawnedZombie = 0;
  }

  public void spawnZombies(int lane) {
    if (isSpawn() && spawnedZombie < numberOfZombie) {
      boolean isAq = false;
      if (lane == 12 || lane == 11) {
        isAq = true;
      }
      System.out.println("Zombie Spawned");
      Zombie zomb = whichZombie(isAq);
      if (zomb instanceof Zombie_Giant) {
        zomb.worldY = (lane - 1) * gp.tileSize - 32;
        zomb.worldX = 32 * gp.tileSize;
      } else {
        zomb.worldY = lane * gp.tileSize - 16;
        zomb.worldX = 32 * gp.tileSize;
      }
      zomb.timeSpawn = gp.elapsedTime;
      for (int i = 0; i < gp.zombie.length; i++) {
        if (gp.zombie[i] == null) {
          gp.zombie[i] = zomb;

          spawnedZombie++;
          System.out.println(spawnedZombie + ", " + numberOfZombie);
          break;
        }
      }

    }
  }

  private boolean isSpawn() {
    Random random = new Random();
    int res = random.nextInt(1, 101);
    if (res <= 30 && res >= 1) {
      return true;
    } else
      return false;
  }

  private Zombie whichZombie(boolean isAq) {
    Random random = new Random();
    Zombie newZombie = null;

    if (!isAq) {

      try {
        newZombie = gp.listOfZombie[random.nextInt(0, 8)].clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    } else {
      try {
        newZombie = gp.listOfZombie[random.nextInt(8, 10)].clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    }
    return newZombie;
  }

  public void update() {
    int arr[] = { 7, 8, 15, 16, 11, 12 };
    if (numberOfZombie <= spawnedZombie) {
      // Check if all zombies are defeated
      boolean isWin = true;
      for (int i = 0; i < gp.zombie.length; i++) {
        if (gp.zombie[i] != null) {
          isWin = false;
          break;
        }
      }
      if (isWin) {
        gp.gameState = gp.endState;
      }

    } else if ((3 + lastSec) == gp.elapsedTime)

    {
      for (int i = 0; i < arr.length; i++) {
        spawnZombies(arr[i]);
      }
      lastSec = gp.elapsedTime;
    }
  }

  public int getSpawnedZombie() {
    return spawnedZombie;
  }

  public int getNumberOfZombie() {
    return numberOfZombie;
  }
}
