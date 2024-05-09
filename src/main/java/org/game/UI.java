package org.game;

import org.object.Object_Egg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage eggImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter =0;
    public String currentDialogue = "";

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Object_Egg objectEgg = new Object_Egg();
        eggImage= objectEgg.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // PLAY STATE
        if(gp.gameState == gp.playState){
            g2.drawImage(eggImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.EggTotal, 74,65);

            // Time
            playTime+=(double) 1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
            // Message
            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        else if(gp.gameState == gp.dialogState){
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawDeckScreen();
            drawInventory();
        }
    }

    public void drawDialogueScreen(){
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2 ;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5, width-10, height-10,25,25);
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public void drawInventory(){
        // FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        // CURSOR
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).up1, slotX, slotY, null);
            slotX += gp.tileSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXStart;
                slotY += gp.tileSize;
            }
        }
        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize/2 + 5;
        g2.setFont(g2.getFont().deriveFont(14F));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX,textY);
                textY+=16;
            }
        }
    }

    public int getItemIndexOnSlot(){
        return  slotCol + (slotRow * 5);
    }

    public void drawDeckScreen(){
        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 7;
        final int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY,frameWidth,frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        // DECK'S ITEMS
        for(int i = 0; i < gp.player.deck.size(); i++){
            g2.drawImage(gp.player.deck.get(i).up1, slotX, slotY, null);
            slotX += gp.tileSize;
            if(i == 5 || i == 10 || i == 15){
                slotX = slotXStart;
                slotY += gp.tileSize;
            }
        }
    }

    private int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
