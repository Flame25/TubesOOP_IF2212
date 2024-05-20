package org.zombies;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.game.GamePanel;
import org.game.LoadImage;

//TODO : Fix Hitbox and spawn coordinate

public class Zombie_Giant extends Zombie {

  public Zombie_Giant(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(1, 8, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    loadAnimations();
  }

  @Override
  protected void loadAnimations() {
    BufferedImage img = LoadImage.GetSpriteAtlas("zombies/Zombie_Giant.png");
    animations = new BufferedImage[3][12];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 32, j * 41, 32, 41);
  }

  @Override
  public void draw(Graphics2D g2) {

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, 32 * 3, 41 * 3, null);
  }
}
