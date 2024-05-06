package org.object;

import org.asset.Zombie;
import org.game.GamePanel;
import org.projectiles.Peashooter_Peas;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Peashooter extends Plants {

    public Peashooter(GamePanel gp, int healthPoint, int damage, int attack_speed)  {
        super(gp,healthPoint,attack_speed,damage);
        getImage();
    }

    @Override
    public void setAction() {

    }
    private void getImage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/plants/Peashooter.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/plants/Peashooter.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {

    }

    @Override
    public void actionAttack(){
        for(int i =0; i< gp.proj.length; i++){
            if(gp.proj[i] == null){
                gp.proj[i] = new Peashooter_Peas();
                gp.proj[i].worldX = this.worldX + gp.tileSize;
                gp.proj[i].worldY = this.worldY - 16;
                break;
            }
        }
    }


//    @Override
//    public Zombie clone() throws CloneNotSupportedException{
//        try {
//            // TODO: copy mutable state here, so the clone can't change the internals of the original
//            return (Zombie) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }
}
