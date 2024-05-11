package org.game;

import javax.swing.*;
import java.awt.*;
import org.asset.*;
import org.map.TileManager;
import org.object.Plants;
import org.object.SuperObject;
import org.projectiles.SuperProjectiles;
import org.zombies.Zombie;

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
	public SuperObject obj[] = new SuperObject[10];
	public SuperProjectiles proj[] = new SuperProjectiles[10];
	public Entity npc[] = new Entity[10];
	public Zombie listOfZombie[] = new Zombie[10]; // List of Available Zombie
	public Zombie zombie[] = new Zombie[10];
	public Plants plants[] = new Plants[10];
	public Player player = new Player(this, keyH);

	// GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 0;
	public final int dialogState = 2;
	public final int characterState = 3;

	// ELAPSED TIME
	public long elapsedTime = 0;

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
		aSetter.setZombie();
		aSetter.setPlants();
		try {
			aSetter.spawnZombie();
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		gameState = playState;
		player = new Player(this,keyH);
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
				elapsedTime++;
				aSetter.setProjectiles(elapsedTime);
				for(int i =0; i < zombie.length; i++){
					if(zombie[i] != null){
						if(elapsedTime % zombie[i].attack_speed == 0){
							zombie[i].actionAttack();
						}
					}
				}
				for(int i =0; i< plants.length; i++){
					if(plants[i] != null){
						if(plants[i].attack_speed !=  0){
							if(elapsedTime % plants[i].attack_speed == 0){
								plants[i].actionAttack();
							}
						}
					}
				}
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

			for(int i =0; i < zombie.length; i++){
				if(zombie[i] != null){
					zombie[i].update();
				}
			}


			for(int i =0; i <plants.length; i++){
				if(plants[i] != null){
					plants[i].update();
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
		// ZOMBIE
		for(int i = 0; i < zombie.length; i++){
			if(zombie[i] != null){
				zombie[i].draw(g2);
			}
		}

		//PLANTS
		for(int i = 0; i< plants.length; i++) {
			if (plants[i] != null) {
				plants[i].draw(g2);
			}
		}

		// Player
		player.draw(g2);

		// UI
		ui.draw(g2);

		g2.dispose();
		
    }
}
