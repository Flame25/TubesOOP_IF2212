package org.projectiles;

import org.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Peashooter_Peas extends SuperProjectiles {

    public Peashooter_Peas(){
        name = "Peashooter Peas";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/plants/PeaShooter_Ball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //TODO : Add Hitbox for this peas
}
