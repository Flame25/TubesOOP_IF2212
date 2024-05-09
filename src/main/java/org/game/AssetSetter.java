package org.game;

import org.asset.NPC;
import org.asset.Zombie_Normal;
import org.object.*;
import org.projectiles.Peashooter_Peas;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setPlants(){
        gp.plants[0] = new Peashooter(gp,125,25,10);
        gp.plants[0].worldX = 23 * gp.tileSize + 3;
        gp.plants[0].worldY = 7 * gp.tileSize - 16;


        gp.plants[1] = new Snowpea(gp,125,25,10);
        gp.plants[1].worldX = 22 * gp.tileSize + 3;
        gp.plants[1].worldY = 7 * gp.tileSize - 16;

        gp.plants[2] = new Lilypad(gp,100, 0, 0);
        gp.plants[2].worldX = 21 * gp.tileSize + 3;
        gp.plants[2].worldY = 7 * gp.tileSize - 16;

    }
    public void setObject(){

        gp.obj[1] = new Object_Egg();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

//        gp.obj[2] = new Wallnut();
//        gp.obj[2].worldX = 23 * gp.tileSize;
//        gp.obj[2].worldY= 8 * gp.tileSize - 14;

    }

    public void setProjectiles(long time){
//        if(time % 10 == 0){
//            for(int i =0; i< gp.proj.length; i++){
//                if(gp.proj[i] == null){
//					gp.proj[i] = new Peashooter_Peas();
//					gp.proj[i].worldX =  24 * gp.tileSize ;
//					gp.proj[i].worldY =  7 * gp.tileSize - 16;
//                    break;
//                }
//            }
//        }
    }

    public void setNPC(){
        gp.npc[0] = new NPC(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 14;
    }

    public void setZombie(){ // Initiation List of Zombie Available
        gp.listOfZombie[0] = new Zombie_Normal(gp,125,3,50, 1); // Initialize Normal Zombie with HP = 125, Speed = 3, Damage = 100
        /*
        How to count speed
        60 Frame per second, zombie walks 1 tile every 5 second and we are using 16 x 16 pixel
        So, zombie walks 3,2 (3) pixels for each second
         */

    }

    public void spawnZombie() throws CloneNotSupportedException { // Need to implement Zombie Spawner as Class
        gp.zombie[0] = gp.listOfZombie[0].clone();
        gp.zombie[0].worldX = gp.tileSize * 31;
        gp.zombie[0].worldY = 7 * gp.tileSize - 16;
    }
}
