package org.object;

import org.game.GamePanel;
import org.projectiles.Peashooter_Peas;
import org.projectiles.Snowpea_Peas;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Snowpea extends Plants{

    public Snowpea(GamePanel gp, int healthPoint, int damage, int attack_speed)  {
        super(gp,healthPoint,attack_speed,damage);
        description = "[" + "Snowpea" + "]\nPeashooter with snow and slow";
        getImage();
    }

    @Override
    public void setAction() {

    }
    private void getImage(){
            up1 = setup("/plants/SnowPea", gp.tileSize,gp.tileSize);
            up1 = setup("/plants/SnowPea", gp.tileSize,gp.tileSize);
    }
    public void update() {

    }

    @Override
    public void actionAttack(){
        for(int i =0; i< gp.proj.length; i++){
            if(gp.proj[i] == null){
                gp.proj[i] = new Snowpea_Peas();
                gp.proj[i].worldX = this.worldX + gp.tileSize;
                gp.proj[i].worldY = this.worldY - 16;
                break;
            }
        }
    }
}