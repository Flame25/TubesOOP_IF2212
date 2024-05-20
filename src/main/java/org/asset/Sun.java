package org.asset;

import java.util.Random;

import org.game.GamePanel;

public class Sun {
    private static int totalSun; 

    public Sun(int totalSun){
        Sun.totalSun = totalSun;
    }

    public static void setTotalSun(int totalSun){
        Sun.totalSun = totalSun;
    }

    public static int getTotalSun(){
        return totalSun;
    }

    public static void generateSun() {
        long sleeptime = 1000;
        GamePanel panel = new GamePanel();

        Random rand = new Random();
        int min = 5;
        int max = 10;
        int interval = rand.nextInt(max - min + 1) + min;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (panel.elapsedTime > 0 && panel.elapsedTime < 100){
                        Thread.sleep(interval * sleeptime);
                        setTotalSun(getTotalSun() + 25);
                    }
                } catch (InterruptedException e) {

                }
            }
        });
        thread.start();
    }
}
