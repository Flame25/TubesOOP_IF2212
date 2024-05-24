package org.game;

import org.object.Object_Egg;
import org.object.Object_Grass;

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
  int messageCounter = 0;
  public String currentDialogue = "";

  double playTime;
  DecimalFormat dFormat = new DecimalFormat("#0.00");
  public int slotCol = 0;
  public int slotRow = 0;
  // INDEX FOR GAME MODE
  public int gameCol = 0;
  public int gameRow = 0;

  public UI(GamePanel gp) {
    this.gp = gp;
    arial_40 = new Font("Arial", Font.PLAIN, 40);
    Object_Egg objectEgg = new Object_Egg();
    eggImage = objectEgg.image;
  }

  public void showMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;
    g2.setFont(arial_40);
    g2.setColor(Color.white);

    // PLAY STATE
    if (gp.gameState == gp.playState) {
      // g2.drawImage(eggImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize,
      // gp.tileSize, null);
      // g2.drawString("x " + gp.player.EggTotal, 74, 65);

      // Time
      g2.drawString("Time: " + gp.elapsedTime, gp.tileSize * 11, 65);
      // Message
      if (messageOn) {
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

        messageCounter++;

        if (messageCounter > 120) {
          messageCounter = 0;
          messageOn = false;
        }
      }
    }
    // PAUSE STATE
    else if (gp.gameState == gp.pauseState) {
      drawPauseScreen();
    } else if (gp.gameState == gp.dialogState) {
      drawDialogueScreen();
    }

    // CHARACTER STATE
    else if (gp.gameState == gp.characterState) {
      drawDeckScreen();
      drawInventory();
    }

    // SLEEP STATE
    else if (gp.gameState == gp.sleepState) {

      playTime += (double) 1 / 60;
      g2.drawString("Time: " + gp.elapsedTime, gp.tileSize * 14, 80);
      drawTilesCursor();
      drawCardSelector();
      drawGrassCount();
    } else if (gp.gameState == gp.endState) {
      drawEndScreen();
    } else if (gp.gameState == gp.zombieAlmnc) {
      drawAlmanac();
    } else if (gp.gameState == gp.helpState) {
      drawHelp();
    }
  }

  public void drawGrassCount() {
    Object_Grass grass = new Object_Grass();
    g2.drawImage(grass.image, gp.tileSize * 15 + 15, 8, gp.tileSize, gp.tileSize, null);
    g2.setFont(g2.getFont().deriveFont(40F));
    g2.drawString(String.valueOf(gp.player.getSun()), gp.tileSize * 16 + 25, 44);
  }

  public void drawTilesCursor() {
    final int startX = 10 * gp.tileSize - 25;
    final int startY = 6 * gp.tileSize + 15;

    int cursorX = startX + (gp.tileSize * gameCol);
    int cursorY = startY + (gp.tileSize * gameRow);
    g2.setColor(Color.white);
    g2.drawRoundRect(cursorX, cursorY, gp.tileSize, gp.tileSize, 10, 10);
  }

  public void drawCardSelector() {
    // WINDOW
    int x = 8;
    int y = 8;
    g2.setColor(Color.white);
    drawSubWindow(x, y, gp.screenWidth - gp.tileSize * 9, gp.tileSize * 2 + 5);
    int tempSlotX = x + 20;
    int tempslotY = y + 20;

    // PLANTS IMAGE
    for (int i = 0; i < gp.player.deck.size(); i++) {
      g2.drawImage(gp.player.deck.get(i).getImage(), tempSlotX, tempslotY, null);
      tempSlotX += gp.tileSize;
    }
    // SUN COST
    tempSlotX = x + 30;
    tempslotY += gp.tileSize + 20;
    boolean addMore = false;
    for (int i = 0; i < gp.player.deck.size(); i++) {
      g2.setFont(g2.getFont().deriveFont(15F));
      String str = "" + gp.player.deck.get(i).getCost();
      if (str.length() == 2 && !addMore) {
        tempSlotX += 5;
        addMore = true;
      } else if (str.length() == 3 && addMore) {
        tempSlotX -= 5;
        addMore = false;
      }
      g2.drawString(str, tempSlotX, tempslotY);
      tempSlotX += gp.tileSize;
    }
  }

  public void drawDialogueScreen() {
    // WINDOW
    int x = gp.tileSize * 2;
    int y = gp.tileSize / 2;
    int width = gp.screenWidth - (gp.tileSize * 4);
    int height = gp.tileSize * 5;
    drawSubWindow(x, y, width, height);

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

    x += gp.tileSize;
    y += gp.tileSize;
    for (String line : currentDialogue.split("\n")) {
      g2.drawString(line, x, y);
      y += 40;
    }
  }

  public void drawSubWindow(int x, int y, int width, int height) {
    Color c = new Color(0, 0, 0, 200);
    g2.setColor(c);
    g2.fillRoundRect(x, y, width, height, 35, 35);

    c = new Color(255, 255, 255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
  }

  public void drawPauseScreen() {
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    String text = "PAUSE";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight / 2;

    g2.drawString(text, x, y);
  }

  public void drawEndScreen() {
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    String text = "";
    String newText = "Esc to Continue";
    if (gp.isGameOver) {
      text = "Game Over";
    } else {
      text = "Win";
    }
    int x = getXforCenteredText(text);
    int y = gp.screenHeight / 2;

    int y2 = gp.screenHeight / 2 + 2 * 48;
    g2.drawString(text, x, y);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45F));

    int x2 = getXforCenteredText(newText);
    g2.drawString(newText, x2, y2);

  }

  public void drawAlmanac() {

    // FRAME
    int frameX = gp.tileSize * 5;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize * 11;
    int frameHeight = gp.tileSize * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    // SLOT
    final int slotXStart = frameX + 20;
    final int slotYStart = frameY + 20;
    int slotX = slotXStart;
    int slotY = slotYStart;

    // CURSOR
    int cursorX = slotXStart + (64 * slotCol);
    int cursorY = slotYStart + (64 * slotRow);
    int cursorWidth = 64;
    int cursorHeight = 64;

    // DRAW PLAYER'S ITEMS
    for (int i = 0; i < gp.listOfZombie.length; i++) {
      g2.drawImage(gp.listOfZombie[i].up1, slotX, slotY, 64, 64, null);
      slotX += 64;
      if (i == 4 || i == 9 || i == 14) {
        slotX = slotXStart;
        slotY += 64 + 5;
      }
    }
    // DRAW CURSOR
    g2.setColor(Color.white);
    g2.drawRoundRect(cursorX, cursorY, 64, 64, 10, 10);

    // DESCRIPTION FRAME
    int dFrameX = frameX;
    int dFrameY = frameY + frameHeight;
    int dFrameWidth = frameWidth;
    int dFrameHeight = gp.tileSize * 3;
    drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

    // DRAW DESCRIPTION TEXT
    int textX = dFrameX + 20;
    int textY = dFrameY + gp.tileSize / 2 + 5;
    g2.setFont(g2.getFont().deriveFont(14F));

    int itemIndex = getItemIndexOnSlot();
    if (itemIndex < gp.listOfZombie.length) {
      for (String line : gp.listOfZombie[itemIndex].description.split("\n")) {
        g2.drawString(line, textX, textY);
        textY += 16;
      }
    }
  }

  public void drawHelp() {

    // FRAME
    int frameX = gp.tileSize * 5;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize * 11;
    int frameHeight = gp.tileSize * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    int dFrameX = frameX;
    int dFrameY = frameY + frameHeight;
    int textX = dFrameX + 25;
    int textY = frameY + 35;
    g2.setFont(g2.getFont().deriveFont(14F));
    String text = "Selamat Datang, pada permainan ini semua pergerakan dilakukan\ndengan 'AWSD'.Permainan bisa dimulai dengan membuka terlebih dahulu\ninventory dengan key 'C' dan memilih tanaman yang ada dengan 'E'.\nKemudian, game bisa dimulai dengan berjalan ke bed dan memencet 'E'\nSelanjutnya, game dimulai dan tanaman bisa ditanamn dengan key '1-6' dan \n'Q' untuk menghilangkan tanaman dari ground.\n\n Selamat Bermain :) \n Info : Tekan H untuk keluar dari menu ini\n Warning : Deck harus terisi 6 tanaman";
    for (String line : text.split("\n")) {
      g2.drawString(line, textX, textY);
      textY += 16;
    }
  }

  public void drawInventory() {
    // FRAME
    int frameX = gp.tileSize * 9;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize * 6;
    int frameHeight = gp.tileSize * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

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
    for (int i = 0; i < gp.player.inventory.size(); i++) {
      g2.drawImage(gp.player.inventory.get(i).up1, slotX, slotY, null);
      slotX += gp.tileSize;
      if (i == 4 || i == 9 || i == 14) {
        slotX = slotXStart;
        slotY += gp.tileSize + 5;
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
    int textY = dFrameY + gp.tileSize / 2 + 5;
    g2.setFont(g2.getFont().deriveFont(14F));

    int itemIndex = getItemIndexOnSlot();
    if (itemIndex < gp.player.inventory.size()) {
      for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
        g2.drawString(line, textX, textY);
        textY += 16;
      }
    }
  }

  public int getItemIndexOnSlot() {
    return slotCol + (slotRow * 5);
  }

  public void drawDeckScreen() {
    // CREATE A FRAME
    final int frameX = gp.tileSize;
    final int frameY = gp.tileSize;
    final int frameWidth = gp.tileSize * 7;
    final int frameHeight = gp.tileSize * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    // TEXT
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(32F));

    final int slotXStart = frameX + 20;
    final int slotYStart = frameY + 20;
    int slotX = slotXStart;
    int slotY = slotYStart;

    // DECK'S ITEMS
    for (int i = 0; i < gp.player.deck.size(); i++) {
      g2.drawImage(gp.player.deck.get(i).up1, slotX, slotY, null);
      slotX += gp.tileSize;
      if (i == 5 || i == 10 || i == 15) {
        slotX = slotXStart;
        slotY += gp.tileSize;
      }
    }
  }

  private int getXforCenteredText(String text) {
    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth / 2 - length / 2;
    return x;
  }
}
