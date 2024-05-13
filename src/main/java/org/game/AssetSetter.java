package org.game;

import org.asset.NPC;
import org.plants.Lilypad;
import org.plants.Peashooter;
import org.plants.Snowpea;
import org.plants.Squash;
import org.zombies.Zombie_BucketHead;
import org.zombies.Zombie_Catapult;
import org.zombies.Zombie_Conehead;
import org.zombies.Zombie_DolphinRider;
import org.zombies.Zombie_DuckyTube;
import org.zombies.Zombie_Football;
import org.zombies.Zombie_Normal;
import org.zombies.Zombie_PoleVaulting;
import org.zombies.Zombie_ScreenDoor;
import org.zombies.Zombie_Snorkle;
import org.object.*;

public class AssetSetter {
  GamePanel gp;

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setPlants() {
    // gp.plants[0] = new Peashooter(gp, 125, 10, -1, 25, false);
    // gp.plants[0].worldX = 23 * gp.tileSize + 3;
    // gp.plants[0].worldY = 7 * gp.tileSize - 16;

    // gp.plants[1] = new Snowpea(gp, 125, 10, -1, 25, false);
    // gp.plants[1].worldX = 22 * gp.tileSize + 3;
    // gp.plants[1].worldY = 7 * gp.tileSize - 16;

    // gp.plants[2] = new Lilypad(gp, 100, 0, 0, 0, true);
    // gp.plants[2].worldX = 21 * gp.tileSize + 3;
    // gp.plants[2].worldY = 12 * gp.tileSize - 16;

  }

  public void setPlantsList() {
    gp.listOfPlants[0] = new Peashooter(gp, 125, 10, -1, 25, false);

    gp.listOfPlants[1] = new Snowpea(gp, 125, 10, -1, 25, false);

    gp.listOfPlants[2] = new Lilypad(gp, 100, 0, 0, 0, true);

    gp.listOfPlants[3] = new Squash(gp, 100, 0, 1, 5000, false);

  }

  public void setObject() {

    gp.obj[1] = new Object_Egg();
    gp.obj[1].worldX = 23 * gp.tileSize;
    gp.obj[1].worldY = 40 * gp.tileSize;

    gp.obj[2] = new Object_Bed();
    gp.obj[2].worldX = 9 * gp.tileSize;
    gp.obj[2].worldY = 4 * gp.tileSize;

    // gp.obj[2] = new Wallnut();
    // gp.obj[2].worldX = 23 * gp.tileSize;
    // gp.obj[2].worldY= 8 * gp.tileSize - 14;

  }

  public void setProjectiles(long time) {
    // if(time % 10 == 0){
    // for(int i =0; i< gp.proj.length; i++){
    // if(gp.proj[i] == null){
    // gp.proj[i] = new Peashooter_Peas();
    // gp.proj[i].worldX = 24 * gp.tileSize ;
    // gp.proj[i].worldY = 7 * gp.tileSize - 16;
    // break;
    // }
    // }
    // }
  }

  public void setNPC() {
    gp.npc[0] = new NPC(gp);
    gp.npc[0].worldX = gp.tileSize * 21;
    gp.npc[0].worldY = gp.tileSize * 14;
  }

  public void setZombie() { // Initiation List of Zombie Available
    gp.listOfZombie[0] = new Zombie_Normal(gp, 125, 3, 50, 1, 1, false); // Initialize Normal Zombie with HP = 125,
                                                                         // Speed = 3, Damage = 100
    gp.listOfZombie[1] = new Zombie_Conehead(gp, 250, 3, 100, 1, 1, false);
    gp.listOfZombie[2] = new Zombie_PoleVaulting(gp, 175, 3, 100, 1, 1, false);
    gp.listOfZombie[3] = new Zombie_BucketHead(gp, 300, 3, 100, 1, 1, false);
    gp.listOfZombie[4] = new Zombie_DuckyTube(gp, 100, 3, 100, 1, 1, true);
    gp.listOfZombie[5] = new Zombie_DolphinRider(gp, 175, 3, 100, 1, 1, true);
    gp.listOfZombie[6] = new Zombie_Football(gp, 300, 3, 200, 3, 1, false);
    gp.listOfZombie[7] = new Zombie_Snorkle(gp, 125, 3, 100, 1, 1, true);
    gp.listOfZombie[8] = new Zombie_Catapult(gp, 175, 3, 75, 4, 1, false);
    gp.listOfZombie[9] = new Zombie_ScreenDoor(gp, 200, 3, 100, 1, 1, false);
    /*
     * How to count speed
     * 60 Frame per second, zombie walks 1 tile every 5 second and we are using 16 x
     * 16 pixel
     * So, zombie walks 3,2 (3) pixels for each second
     */

  }

  public void spawnZombie() throws CloneNotSupportedException { // Need to implement Zombie Spawner as Class
    gp.zombie[0] = gp.listOfZombie[0].clone();
    gp.zombie[0].worldX = gp.tileSize * 31;
    gp.zombie[0].worldY = 7 * gp.tileSize - 16;
  }
}
