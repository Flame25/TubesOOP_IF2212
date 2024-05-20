package org.zombies;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.game.GamePanel;
import org.game.LoadImage;

public class Zombie_Bread extends Zombie {

  public Zombie_Bread(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
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
  protected void loadAnimations() {
    BufferedImage img = LoadImage.GetSpriteAtlas("zombies/Zombie_Toast.png");
    animations = new BufferedImage[2][4];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 16, j * 16, 16, 16);
  }
}
