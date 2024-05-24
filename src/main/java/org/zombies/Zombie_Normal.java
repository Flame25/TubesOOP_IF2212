package org.zombies;

import org.game.GamePanel;
import org.game.LoadImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Zombie_Normal extends Zombie {
  // DONE: CREATE BASE ZOMBIE CLASS

  public Zombie_Normal(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 2, 32, 12);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfRunning = 8;
    numOfIdle = 4;
    state = numOfIdle;
    loadAnimations(2, 8, "zombies/Zombie_Normal_F.png", 18, 18);
  }
}
