package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int maxScreenRow = gamePanel.maxScreenRow;
        int maxScreenCol = gamePanel.maxScreenCol;

        int tileSize = gamePanel.tileSize;

        int X = 0;
        int Y = 0;

        for(int row = 0; row < maxScreenRow/2; row++){
            X = row * tileSize;
            for(int col = 0; col < maxScreenCol/2; col++){
                Y = col * tileSize;
                g2.drawImage(tiles[0].image, Y, X, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }

    }
}
