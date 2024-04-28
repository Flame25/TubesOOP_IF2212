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
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[40];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadMap();
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
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile056.png"));

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

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/maps01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row <gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col =0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
