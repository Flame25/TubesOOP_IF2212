package org.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Peashooter extends SuperObject{
    public Object_Peashooter(){
        name = "Peashooter";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/plants/Peashooter.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
