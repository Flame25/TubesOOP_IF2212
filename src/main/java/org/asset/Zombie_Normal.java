package org.asset;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Zombie_Normal extends Zombie {
    // DONE: CREATE ABSTRACT ZOMBIE CLASS

    public Zombie_Normal(GamePanel gp, int healthPoint, int speed) {
        super(gp, healthPoint, speed);
        this.gp = gp;
        this.healthPoint = healthPoint;
        this.speed = speed;
        direction = "left";
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getPlayerImage();
    }

    @Override
    public void setAction() {

    }


    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
