package org.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Egg extends  SuperObject{
    public Object_Egg(){
        name = "Egg";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/egg_item.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
