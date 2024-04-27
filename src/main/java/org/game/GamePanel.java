package org.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable {
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread; // Game Clock
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // Passing game panel to thread
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null){
            System.out.printf("Game is running....");
        }
    }
}
