package org.zombies;

import org.asset.Action;
import org.game.GamePanel;

import java.awt.*;

public class Zombie_Football extends Zombie implements Action {

    /* TODO
    - Copy this class
    - Add attack (actionAttack) if there is any difference with normal attack and Special Skill in setAction
    - Add zombie to zombie list (look at setZombie() at AssetSetter
     */
    public Zombie_Football(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed,  boolean isAquatic) {
        super(gp, 300, speed, 200, 3, false);
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
    public void setAction(){
        
    }

    public void getPlayerImage() {
    }
}
