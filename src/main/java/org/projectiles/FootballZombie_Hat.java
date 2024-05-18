package org.projectiles;
import java.awt.*;
import java.awt.image.BufferedImage;

import org.game.GamePanel;
import org.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
public class FootballZombie_Hat extends SuperProjectiles{


    //TODO make the hat projectille smaller because it's y coordinate intersect with more than 1 tile
    public FootballZombie_Hat(){
        name = "Football Hat";
        collision = true;
        statusEffect = "None";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/plant_vs_zombie_pixel_image/zomble/football_hat.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(){
        if(!collisionOn){
            worldX -= speed;
        }
    }
}
