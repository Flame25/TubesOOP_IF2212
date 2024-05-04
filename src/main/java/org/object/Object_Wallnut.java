package org.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Wallnut extends  SuperObject {

    public Object_Wallnut(){
        name = "Wallnut";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/plants/Wallnut.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
