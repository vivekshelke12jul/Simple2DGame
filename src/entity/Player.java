package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
        screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){


        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch(IOException e){
            up1 = null;
            up2 = null;
            down1 = null;
            down2 = null;
            left1 = null;
            left2 = null;
            right1 = null;
            right2 = null;
            e.printStackTrace();
        }

    }

    public void update(){

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){

            if(keyHandler.upPressed){
                direction = "up";
                worldY -= speed;
            }
            if(keyHandler.downPressed){
                direction = "down";
                worldY += speed;
            }
            if(keyHandler.leftPressed){
                direction = "left";
                worldX -= speed;
            }
            if(keyHandler.rightPressed){
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                spriteNum = 1 - spriteNum;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image;
        if(spriteNum == 0){
            image = switch (direction) {
                case "left" -> left1;
                case "right" -> right1;
                case "up" -> up1;
                case "down" -> down1;
                default -> null;
            };
        }else{
            image = switch (direction) {
                case "left" -> left2;
                case "right" -> right2;
                case "up" -> up2;
                case "down" -> down2;
                default -> null;
            };
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
