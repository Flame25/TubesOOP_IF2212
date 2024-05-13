package org.zombies;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Zombie_Normal extends Zombie {
    // DONE: CREATE BASE ZOMBIE CLASS

    public Zombie_Normal(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed,  boolean isAquatic) {
        super(gp, 125, speed, 100, 1, false);
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
            speed = defaultSpeed;
            counter =0;
            howManySecs++;
        }
        super.update();
        this.speed = 0;
        counter++;
    }

    


    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormal_Left1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormal_Left2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormalFreezed_Left1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormalFreezed_Left2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormal_Left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/zombies/ZombieNormal_Left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
