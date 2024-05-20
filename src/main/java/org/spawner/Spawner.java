package org.spawner;

import java.util.Random;

import org.asset.Action;
import org.game.GamePanel;
import org.zombies.Zombie;

public class Spawner {
  private GamePanel gp;
  private long lastSec = 99999;

  public Spawner(GamePanel gp) {
    this.gp = gp;
    lastSec = gp.elapsedTime;
  }

  public void spawnZombies(int lane) {
    if (isSpawn()) {
      System.out.println("Zombie Spawned");
      Zombie zomb = whichZombie();
      for (int i = 0; i < gp.zombie.length; i++) {
        if (gp.zombie[i] == null) {
          gp.zombie[i] = zomb;
          gp.zombie[i].worldX = 32 * gp.tileSize;
          gp.zombie[i].worldY = lane * gp.tileSize;
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

  private Zombie whichZombie() {
    Random random = new Random();
    Zombie newZombie = null;
    try {
      newZombie = gp.listOfZombie[1].clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return newZombie;
  }

  public void update() {
    int arr[] = { 7, 8, 15, 16 };
    if ((4 + lastSec) == gp.elapsedTime) {
      for (int i = 0; i < arr.length; i++) {
        spawnZombies(arr[i]);
      }
      lastSec = gp.elapsedTime;
    }
  }
}
