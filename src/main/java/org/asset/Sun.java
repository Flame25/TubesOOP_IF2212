package org.asset;

import java.util.Random;
import org.game.GamePanel;

class Sun {
  private static Sun newSun = null;

  private int totalSun = 100;

  public void setTotalSun(int sunNumber) {
    newSun.totalSun = sunNumber;
  }

  public int getTotalSun() {
    return newSun.totalSun;
  }

  public static synchronized Sun getInstance() {
    if (newSun == null) {
      newSun = new Sun();
    }
    return newSun;
  }

  public static void generateSun() {
    long sleeptime = 1000;
    GamePanel panel = new GamePanel();

    Random rand = new Random();
    int min = 5;
    int max = 10;
    int interval = rand.nextInt(max - min + 1) + min;

    Sun sun = new Sun();
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                if (panel.elapsedTime > 0 && panel.elapsedTime < 100){
                    Thread.sleep(interval * sleeptime);
                    sun.setTotalSun(sun.getTotalSun() + 25);
                }
            } catch (InterruptedException e) {

            }
        }
    });
    thread.start();
    }
}
