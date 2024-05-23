package org.projectiles;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.game.GamePanel;

public class Cactus_Proj extends SuperProjectiles {

  public Cactus_Proj() {
    name = "Cactus Projectiles";
    collision = true;
    statusEffect = "None";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/plants/bullet_cactus.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void draw(Graphics2D g2, GamePanel gp) {

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
      g2.drawImage(image, screenX, screenY, 24, 24, null);
    }
  }

}
