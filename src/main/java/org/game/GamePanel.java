package org.game;

import javax.swing.*;
import java.awt.*;
import org.asset.*;
import org.map.TileManager;

public class GamePanel extends JPanel implements  Runnable {
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FPS 
	int FPS = 60;

	TileManager tileManager = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); 
	Thread gameThread; // Game Clock
	Player player = new Player(this, keyH);
	NPC npc = new  NPC(this);

	int playerX = 100; 
	int playerY = 100; 
	int playerSpeed = 4;
    boolean rightPres = false;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(155,212,195));
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
		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		int drawCount = 0;
		long timer = 0;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				timer = 0;
				drawCount = 0;
			}
        }
    }

    public void update(){
		player.update();
		npc.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

		tileManager.draw(g2);
		player.draw(g2);
		npc.draw(g2);
		g2.dispose();
		
    }
}
