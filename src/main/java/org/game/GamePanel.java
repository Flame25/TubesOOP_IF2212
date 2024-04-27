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
	
	KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; // Game Clock
	
	int playerX = 100; 
	int playerY = 100; 
	int playerSpeed = 4;
    boolean rightPres = false;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(keyH);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // Passing game panel to thread
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null){
            System.out.printf("Game is running....");
            // TODO : 1. UPDATE game state
            update();
            // TODO : 2. Draw screen and information
            repaint();
        }
    }

    public void update(){
		if(keyH.downPressed == true){
			playerY += playerSpeed; 
		}
		else if(keyH.upPressed == true){
			playerY -= playerSpeed;
		}
		else if(keyH.leftPressed == true){
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true){
			playerX += playerSpeed;
            rightPres = true;
		}
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
		if(rightPres){
            g2.setColor(Color.CYAN);
        }
        else
            g2.setColor(Color.RED);
		g2.fillRect(playerX, playerY, tileSize, tileSize);

		g2.dispose();
		
    }
}
