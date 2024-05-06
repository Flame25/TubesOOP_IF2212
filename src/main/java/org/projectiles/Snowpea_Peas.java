package org.projectiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Snowpea_Peas extends  SuperProjectiles{

    public Snowpea_Peas(){
        name = "Snowpea Peas";
        collision = true;
        statusEffect = "Slow";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/plants/PeaSnow_Ball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
