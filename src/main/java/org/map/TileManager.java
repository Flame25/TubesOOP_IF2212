package org.map;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[40];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world02.txt");
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_round_grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/up_lim_grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_round_grass.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_lim_grass.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/middle_grass.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_lim_grass.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftDown_round_grass.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/down_lim_grass.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightDown_grass.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/righRounded_grass.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftRounded_grass.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightDR_grass.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftDR_grass.png"));

            // Tiled Dirt

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_round.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_round2.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_up.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_plant.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_down_round.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_down.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_down_round2.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_left.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_right.png"));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/no_door.png"));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_upper_wall.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/up_middle_wall.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_upper_wall.png"));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/middle_wall.png"));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_wall.png"));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_down_wall.png"));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_wall.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_down_wall.png"));
            tile[31].collision = true;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path){
        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row <gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow< gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol== gp.maxWorldCol){
                worldCol= 0;
                worldRow++;
            }
        }
    }
}
