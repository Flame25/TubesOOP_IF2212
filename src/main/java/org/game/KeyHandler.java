package org.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, eKeyPressed;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public KeyHandler(GamePanel gp){
		this.gp = gp;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		// PLAY STATE
		if(gp.gameState == gp.playState){
			if(code == KeyEvent.VK_W){
				upPressed = true;
			}
			if(code == KeyEvent.VK_S){
				downPressed = true;
			}
			if(code == KeyEvent.VK_A){
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D){
				rightPressed = true;
			}
			if(code == KeyEvent.VK_ESCAPE){
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_E){
				eKeyPressed = true;
			}
			if(code == KeyEvent.VK_C){
				gp.gameState = gp.characterState;
			}
		}

		// PAUSE STATE
		else if(gp.gameState == gp.pauseState){

			if(code == KeyEvent.VK_ESCAPE){
				gp.gameState = gp.playState;
			}
		}

		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogState){
			if(code == KeyEvent.VK_ESCAPE){
				gp.gameState = gp.playState;
			}
		}
		else if(gp.gameState == gp.characterState){
			if(code  == KeyEvent.VK_C){
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_W){
				if(gp.ui.slotRow != 0){
					gp.ui.slotRow--;
				}
			}
			if(code == KeyEvent.VK_A){
				if(gp.ui.slotCol !=0){
					gp.ui.slotCol--;
				}
			}
			if(code == KeyEvent.VK_D){
				if(gp.ui.slotCol!= 4){
					gp.ui.slotCol++;
				}
			}
			if(code == KeyEvent.VK_S){
				if(gp.ui.slotRow != 3){
					gp.ui.slotRow++;
				}
			}
			if(code == KeyEvent.VK_E){
				if(!gp.player.deck.contains(gp.plants[gp.ui.getItemIndexOnSlot()])){
					gp.player.deck.add(gp.plants[gp.ui.getItemIndexOnSlot()]);
				}

			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W){
			upPressed = false; 
		}
		if(code == KeyEvent.VK_S){
			downPressed = false; 
		}
		if(code == KeyEvent.VK_A){
			leftPressed = false; 
		}
		if(code == KeyEvent.VK_D){
			rightPressed = false; 
		}
	}

}
