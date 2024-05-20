package org.zombies;

import org.game.GamePanel;
import org.game.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zombie_Conehead extends Zombie {

  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public Zombie_Conehead(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    numOfIdle = 7;
    numOfRunning = 7;
  }

  @Override
  public void update() {
    if (counter >= 60) {
      speed = 3;
      counter = 0;
    }
    super.update();
    this.speed = 0;
    counter++;
  }

  @Override
  protected void loadAnimations() {
    BufferedImage img = LoadImage.GetSpriteAtlas("zombies/Zombie_Conehead.png");
    animations = new BufferedImage[2][7];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = img.getSubimage(i * 16, j * 16, 16, 16);
  }
}
