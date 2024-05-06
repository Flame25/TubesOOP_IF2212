package org.object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wallnut extends Plants {

    public Wallnut(GamePanel gp, int healthPoint, int attack_speed, int damage){
        super(gp, healthPoint, attack_speed, damage);
        collision = true;

    }
}
