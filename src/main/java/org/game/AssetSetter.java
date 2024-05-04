package org.game;

import org.asset.NPC;
import org.object.Object_Egg;
import org.object.Object_Peashooter;
import org.object.Object_Wallnut;
import org.projectiles.Peashooter_Peas;
import org.projectiles.SuperProjectiles;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Object_Peashooter();
        gp.obj[0].worldX = 23 * gp.tileSize + 3;
        gp.obj[0].worldY = 7 * gp.tileSize - 16;

        gp.obj[1] = new Object_Egg();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new Object_Wallnut();
        gp.obj[2].worldX = 23 * gp.tileSize;
        gp.obj[2].worldY= 8 * gp.tileSize - 14;

    }

    public void setProjectiles(long time){
        if(time % 10 == 0){
            for(int i =0; i< gp.proj.length; i++){
                if(gp.proj[i] == null){
					gp.proj[i] = new Peashooter_Peas();
					gp.proj[i].worldX =  24 * gp.tileSize ;
					gp.proj[i].worldY =  7 * gp.tileSize - 16;
                    break;
                }
            }
        }
    }

    public void setNPC(){
        gp.npc[0] = new NPC(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 14;
    }
}
