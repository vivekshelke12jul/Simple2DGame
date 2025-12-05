package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            for(; row< gamePanel.maxWorldRow; row++){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for(col = 0; col< gamePanel.maxWorldCol; col++){
                    mapTileNum[row][col] = Integer.parseInt(numbers[col]);
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){


        int tileSize = gamePanel.tileSize;

        int worldX = 0;
        int worldY = 0;

        int screenX = 0;
        int screenY = 0;


        for(int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++){
            worldX = worldRow * tileSize;
            for(int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++){
                worldY = worldCol * tileSize;

                int tileNum = mapTileNum[worldRow][worldCol];
                screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                if(
                        screenX + gamePanel.tileSize > 0 &&
                        screenX < gamePanel.screenWidth &&
                        screenY + gamePanel.tileSize > 0 &&
                        screenY < gamePanel.screenHeight
                ){
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}
