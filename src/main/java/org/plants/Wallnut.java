package org.plants;

import org.game.GamePanel;

public class Wallnut extends Plants {

    public Wallnut(GamePanel gp, int healthPoint, int attack_speed, int damage){
        super(gp, healthPoint, attack_speed, damage);
        collision = true;

    }
}
