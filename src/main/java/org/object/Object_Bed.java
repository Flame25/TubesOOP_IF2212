package org.object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object_Bed extends  SuperObject{
    public BufferedImage image_bottom;
    public Object_Bed(){
        name = "bed";
        collision = true;
        solidArea.height = 96;

        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/bed_1.png"));
            image_bottom = ImageIO.read(getClass().getResourceAsStream("/objects/bed_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void imageToDef(){
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/bed_1.png"));
            image_bottom = ImageIO.read(getClass().getResourceAsStream("/objects/bed_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        super.draw(g2, gp);

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY + gp.tileSize;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image_bottom, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
