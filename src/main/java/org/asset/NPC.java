package org.asset;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity{
    GamePanel gp;
    BufferedImage left1, left2, right1, right2, left_idle;
    int changeStep = 0;
    public NPC(GamePanel gp){
        this.gp = gp;
        setDefaultValues();
        getNPCImage();
    }

    public void setDefaultValues() {
        worldX = 300;
        worldY = 150;
        speed = 4;
        direction = "left";
    }
    private void getNPCImage(){
        try{
            left1= ImageIO.read(getClass().getResourceAsStream("/npc/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/npc/left2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/npc/right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/npc/right2.png"));
            left_idle = ImageIO.read(getClass().getResourceAsStream("/npc/left_idle.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(direction.equals("right")){
            worldX += speed;
        }
        else if(direction.equals("left")){
            worldX -= speed;
        }
        spriteCounter++;
        changeStep++;
        if(spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(changeStep > 50){
            changeStep = 0;
            if(direction.equals("left")){
                direction = "right";
            }
            else if(direction.equals("right")){
                direction = "left";
            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up" :
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down" :
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;

            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image,worldX,worldY, gp.tileSize, gp.tileSize, null);
    }
}
