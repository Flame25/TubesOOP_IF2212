package org.asset;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Zombie_Template extends Zombie{

    public Zombie_Template(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed) {
        super(gp, healthPoint, speed, damage, attack_speed);
        direction = "left";
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        counter =0;
        getPlayerImage();
    }



    @Override
    public void update() {
        if(counter>= 60){
            speed = 3;
            counter =0;
        }
        super.update();
        this.speed = 0;
        counter++;
    }

    @Override
    public void setAction() {
    }


    public void getPlayerImage() {
    }
}
