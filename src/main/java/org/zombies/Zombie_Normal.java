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
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    loadAnimations();
  }

  @Override
  public void update() {
    if (counter >= 60) {
      speed = defaultSpeed;
      counter = 0;
      howManySecs++;
    }
    super.update();
    this.speed = 0;
    counter++;
  }

  @Override
  protected void loadAnimations() {
    BufferedImage img = LoadImage.GetSpriteAtlas("zombies/Zombie_Normal_F.png");
    animations = new BufferedImage[2][8];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 18, j * 18, 18, 18);
  }

  @Override
  public void draw(Graphics2D g2) {

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }
}
