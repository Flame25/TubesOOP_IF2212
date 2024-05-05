package org.game;

import javax.swing.*;
import java.awt.*;
import org.asset.*;
import org.map.TileManager;
import org.object.SuperObject;
import org.projectiles.Peashooter_Peas;
import org.projectiles.SuperProjectiles;

public class GamePanel extends JPanel implements  Runnable {
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// FPS 
	int FPS = 60;

	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// SYSTEM
	TileManager tileManager = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Thread gameThread; // Game Clock
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);

	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public SuperProjectiles proj[] = new SuperProjectiles[10];
	public Entity npc[] = new Entity[10];

	// GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 0;
	public final int dialogState = 2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(155,212,195));
        this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(keyH);
    }

	public void setupGame(){
		aSetter.setObject();
		aSetter.setNPC();
		gameState = playState;
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
		int drawCount = 0
				;
		long timer = 0;
		long elapsedTime = 0;
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
				elapsedTime++;
				aSetter.setProjectiles(elapsedTime);
				timer = 0;
				drawCount = 0;
			}
        }
    }

    public void update() {
		if(gameState == playState){
			player.update();

			for(int i =0; i< proj.length; i++){
				if(proj[i] != null) {
					if (proj[i].worldX >= 50 * tileSize) {
						proj[i] = null;
					} else {
						proj[i].update();
					}
				}
			}

			for(int i =0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].update();
				}
			}
		}
		else if(gameState == pauseState){

		}
	}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

		// Tile
		tileManager.draw(g2);
		// Object
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }
		// Projectiles
		for(int i =0; i < proj.length; i++){
			if(proj[i] != null)
				proj[i].draw(g2,this);
			}
		// NPC
		for(int i =0; i < npc.length; i++){
			if(npc[i] != null){
				npc[i].draw(g2);
			}
		}
		// Player
		player.draw(g2);

		// UI
		ui.draw(g2);
		g2.dispose();
		
    }


}
