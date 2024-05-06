package org.asset;

import org.game.GamePanel;

public class Zombie extends Entity{
    GamePanel gp;
    int healthPoint;
    public Zombie(GamePanel gp, int healthPoint, int speed){ // TODO : ADD MORE ATTRIBUTES
        super(gp);
        super.speed = speed;
        this.healthPoint = healthPoint;
    }
    @Override
    public void setAction() {

    }


    public void update() {
        super.update();
        int projIndex = gp.cChecker.checkProjectile(this);
        actionProjectiles(projIndex);
    }

    private void actionProjectiles(int i) {
        if (i != 9999) {
            healthPoint -= 5;
            System.out.println("Zombie Hit");
            gp.proj[i] = null;
            if (healthPoint <= 0) {
                System.out.println("Zombie dead");
                for (int j = 0; j < gp.zombie.length; j++) {
                    if(gp.zombie[j] != null){
                        if (gp.zombie[j].equals(this)) {
                            gp.zombie[j] = null;
                        }
                    }
                }
            }
        }
    }
}
