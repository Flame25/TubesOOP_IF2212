package org.projectiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Purple_Projectiles extends SuperProjectiles {

  public Purple_Projectiles() {
    name = "Purple Mushroom Projectiles";
    collision = true;
    statusEffect = "None";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/plants/Purple_Ball.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
