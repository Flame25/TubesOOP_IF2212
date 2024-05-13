package org.zombies;

import org.asset.Entity;
import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Zombie extends Entity implements Cloneable {
  int healthPoint;
  boolean isAquatic;
  public int attack_speed;
  int counter = 0; // 60 fps, 1 time walk for each repetition
  int howManySecs = 0;
  int damage = 0;
  int defaultSpeed;
  String statusEffect;
  int attack_range;

  public Zombie(GamePanel gp, int healthPoint, int speed, int damage, int attack_speed, int attack_range,
      boolean isAquatic) { // TODO : ADD MORE ATTRIBUTES
    super(gp);
    this.attack_range = attack_range;
    this.speed = speed;
    this.healthPoint = healthPoint;
    this.damage = damage;
    this.attack_speed = attack_speed;
    this.defaultSpeed = 3;
    this.statusEffect = "None";
    this.isAquatic = isAquatic;

  }

  public void update() {
    super.update();
    gp.cChecker.checkObject(this, true);
    int projIndex = gp.cChecker.checkProjectile(this);
    actionProjectiles(projIndex);
    if (statusEffect.equals("Slowed")) {
      if (howManySecs >= 3) {
        statusEffect = "None";
        defaultSpeed = 3;
        left1 = up1;
        left2 = up2;
      } else {
        left1 = down1;
        left2 = down2;
        defaultSpeed = 1;
      }
    }
    gp.cChecker.checkEntity(this, gp.plants);
  }

  @Override
  public void setAction() {

  }

  public void actionAttack() {
    if (gp.elapsedTime % attack_speed == 0) { // Is this good practice? probably :)
      int plantIndex = gp.cChecker.checkEntity(this, gp.plants);
      if (plantIndex != 9999) {
        gp.plants[plantIndex].healthPoint -= damage;
        System.out.println("Zombie Attack");
        if (gp.plants[plantIndex].healthPoint <= 0) {
          gp.plants[plantIndex] = null;
        }
      }
    }
  }

  private void actionProjectiles(int i) {
    if (i != 9999) {
      healthPoint -= 25;
      System.out.println("Zombie Hit");
      if (gp.proj[i].name.equals("Snowpea Peas")) {
        this.howManySecs = 0;
        this.statusEffect = "Slowed";
        System.out.println("Zombie Slowed");
      }
      gp.proj[i] = null;
      if (healthPoint <= 0) {
        System.out.println("Zombie dead");
        for (int j = 0; j < gp.zombie.length; j++) {
          if (gp.zombie[j] != null) {
            if (gp.zombie[j].equals(this)) {
              gp.zombie[j] = null;
            }
          }
        }
      }
    }
  }

  @Override
  public Zombie clone() throws CloneNotSupportedException {
    try {
      // TODO: copy mutable state here, so the clone can't change the internals of the
      // original
      return (Zombie) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
