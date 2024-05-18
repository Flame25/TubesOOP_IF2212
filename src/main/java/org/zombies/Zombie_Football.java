package org.zombies;

import org.asset.Action;
import org.game.GamePanel;
import org.projectiles.FootballZombie_Hat;
import org.projectiles.Peashooter_Peas;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Zombie_Football extends Zombie {
  
  /*
   * TODO
   * - Copy this class
   * - Add attack (actionAttack) if there is any difference with normal attack and
   * Special Skill in setAction
   * - Add zombie to zombie list (look at setZombie() at AssetSetter
   */
  public Zombie_Football(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) {
    super(gp, healthPoint, speed, damage, attack_speed, attack_range, isAquatic);
    direction = "left";
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    counter = 0;
    getPlayerImage();
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
  public void actionAttack() {
    //throw hat to plants from a certain range (ranged damage dealer)

    if (gp.elapsedTime % attack_speed == 0) { // Is this good practice? probably :)
      
      for (int i = 0; i < gp.proj.length; i++) {
        if (gp.proj[i] == null) {
          gp.proj[i] = new FootballZombie_Hat();
          gp.proj[i].worldX = this.worldX - gp.tileSize;
          gp.proj[i].worldY = this.worldY - 16;
          break;
        }
      }
      
      
    }

    
  }
  @Override
  public void setAction(){

  }
  public void getPlayerImage() {

    try {
      
      
      left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/plant_vs_zombie_pixel_image//zomble//football1.png")));
      left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/plant_vs_zombie_pixel_image//zomble//football1.png")));
      
      
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
