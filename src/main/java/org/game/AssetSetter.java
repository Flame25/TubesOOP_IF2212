package org.game;

import org.asset.NPC;
import org.plants.PotatoMine;
import org.plants.PurpleMushroom;
import org.plants.Cactus;
import org.plants.Lilypad;
import org.plants.Peashooter;
import org.plants.PotatoMine;
import org.plants.Snowpea;
import org.plants.Squash;
import org.plants.Sunflower;
import org.plants.Wallnut;
import org.plants.YellowMushroom;
import org.zombies.Zombie_Brawler;
import org.zombies.Zombie_Bread;
import org.zombies.Zombie_BucketHead;
import org.zombies.Zombie_Conehead;
import org.zombies.Zombie_DolphinRider;
import org.zombies.Zombie_DuckyTube;
import org.zombies.Zombie_Giant;
import org.zombies.Zombie_Normal;
import org.zombies.Zombie_PoleVaulting;
import org.zombies.Zombie_Slime;
import org.object.*;

public class AssetSetter {
  GamePanel gp;

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setPlants() {
    // gp.plants[1] = new Snowpea(gp, 125, 10, -1, 25, false);
    // gp.plants[1].worldX = 22 * gp.tileSize + 3;
    // gp.plants[1].worldY = 7 * gp.tileSize - 16;

    // gp.plants[2] = new Lilypad(gp, 100, 0, 0, 0, true);
    // gp.plants[2].worldX = 21 * gp.tileSize + 3;
    // gp.plants[2].worldY = 12 * gp.tileSize - 16;

  }

  public void setPlantsList() {
    gp.listOfPlants[0] = new Peashooter(gp, 125, 10, -1, 25, 100, 10, false);

    gp.listOfPlants[1] = new Snowpea(gp, 125, 10, -1, 25, 175, 10, false);

    gp.listOfPlants[2] = new Lilypad(gp, 100, 0, 0, 0, 25, 10, true);

    gp.listOfPlants[3] = new Squash(gp, 100, 0, 1, 5000, 50, 20, false);

    gp.listOfPlants[4] = new Wallnut(gp, 1000, 0, 0, 0, 50, 20, false);

    gp.listOfPlants[5] = new Sunflower(gp, 100, 0, 0, 0, 50, 10, false);

    gp.listOfPlants[6] = new YellowMushroom(gp, 50, 0, 0, 0, 25, 10, false);

    gp.listOfPlants[7] = new PotatoMine(gp, 50, 1, 1, 9999, 25, 10, false);

    gp.listOfPlants[8] = new PurpleMushroom(gp, 50, 10, -1, 15, 0, 10, false);

    gp.listOfPlants[9] = new Cactus(gp, 125, 5, -1, 25, 125, 10, false);

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
    gp.listOfZombie[1] = new Zombie_Giant(gp, 500, 3, 200, 1, 1, false); // worldY -32
    gp.listOfZombie[2] = new Zombie_Conehead(gp, 250, 3, 100, 1, 1, false);
    gp.listOfZombie[3] = new Zombie_Bread(gp, 100, 3, 100, 1, 1, false);
    gp.listOfZombie[6] = new Zombie_Slime(gp, 300, 3, 200, 3, 1, false);
    gp.listOfZombie[7] = new Zombie_Brawler(gp, 125, 3, 100, 1, 1, false);
    gp.listOfZombie[4] = new Zombie_PoleVaulting(gp, 175, 3, 75, 4, 1, false);
    gp.listOfZombie[5] = new Zombie_BucketHead(gp, 100, 3, 100, 1, 1, false);
    gp.listOfZombie[9] = new Zombie_DolphinRider(gp, 175, 3, 100, 1, 1, true);
    gp.listOfZombie[8] = new Zombie_DuckyTube(gp, 100, 3, 100, 1, 1, true);
    /*
     * How to count speed
     * 60 Frame per second, zombie walks 1 tile every 5 second and we are using 16 x
     * 16 pixel
     * So, zombie walks 3,2 (3) pixels for each second
     */

  }

  public void spawnZombie() throws CloneNotSupportedException { // Need to implement Zombie Spawner as Class
    // gp.zombie[0] = gp.listOfZombie[1].clone();
    // gp.zombie[0].worldX = gp.tileSize * 31;
    // gp.zombie[0].worldY = 7 * gp.tileSize - 32;
  }
}
