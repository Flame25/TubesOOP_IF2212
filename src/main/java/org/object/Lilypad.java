package org.object;

import org.game.GamePanel;
import org.projectiles.Snowpea_Peas;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Lilypad extends Plants{
    public Lilypad(GamePanel gp, int healthPoint, int damage, int attack_speed){
        super(gp,healthPoint,attack_speed,damage);
        direction = "up";
        description = "[" + "Lilypad" + "]\nHello, I am Lilypad";
        getImage();
    }

    public void getImage(){
        up1 = setup("/plants/Lilypad", gp.tileSize, gp.tileSize);
        up2 = setup("/plants/Lilypad", gp.tileSize, gp.tileSize);
//        try{
//            up1= ImageIO.read(getClass().getResourceAsStream("/plants/Lilypad.png"));
//            up2= ImageIO.read(getClass().getResourceAsStream("/plants/Lilypad.png"));
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }


    @Override
    public void actionAttack(){
    }
}
