package org.plants;

import org.game.GamePanel;

public class Lilypad extends Plants {
  public Lilypad(GamePanel gp, int healthPoint, int attack_speed, int range, int damage, int cost, int cooldown,
      boolean is_aquatic) {
    super(gp, healthPoint, attack_speed, range, damage, cost, cooldown, is_aquatic);
    direction = "up";
    description = "[" + "Lilypad" + "]\nHello, I am Lilypad";
    statusOn = true;
    loadImage();
    image = up1;
  }

  public void loadImage() {
    up1 = setup("/plants/Lilypad", gp.tileSize, gp.tileSize);
    up2 = setup("/plants/Lilypad", gp.tileSize, gp.tileSize);
    down1 = setup("/plants/Lilypad_Off", gp.tileSize, gp.tileSize);
    // try{
    // up1= ImageIO.read(getClass().getResourceAsStream("/plants/Lilypad.png"));
    // up2= ImageIO.read(getClass().getResourceAsStream("/plants/Lilypad.png"));
    // }catch(IOException e){
    // e.printStackTrace();
    // }
  }

  @Override
  public void actionAttack() {
  }
}
