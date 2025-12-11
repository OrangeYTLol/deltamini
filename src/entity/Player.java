package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel panel;
    KeyHandler keyH;

    public Player(GamePanel panel, KeyHandler keyH) {
        // Declare default values and initialize player
        this.panel = panel;
        this.keyH = keyH;
        this.hitbox = new Rectangle(this.x + panel.scale, this.y + 8 * panel.scale, 14 * panel.scale, 8 * panel.scale);
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = this.panel.screenWidth / 2;
        this.y = this.panel.screenHeight / 2;
        this.speed = 12;
        this.direction = "down";
    }

    // Load player sprites
    public void getPlayerImage() {
        try {
            this.up1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/up1.png"));
            this.up2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/up2.png"));
            this.left1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/left1.png"));
            this.left2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/left2.png"));
            this.down1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/down1.png"));
            this.down2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/down2.png"));
            this.right1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/right1.png"));
            this.right2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        // Check for key inputs and set direction if true
        if (this.keyH.upPressed) {
            this.direction = "up";
        }

        if (this.keyH.downPressed) {
            this.direction = "down";
        }

        if (this.keyH.leftPressed) {
            this.direction = "left";
        }

        if (this.keyH.rightPressed) {
            this.direction = "right";
        }

        // Move if no collisions
        this.collided = false;
        this.panel.collisionH.checkTile(this);
        if (!this.collided) {
            if (this.keyH.upPressed) {
                this.y -= this.speed;
            }

            if (this.keyH.downPressed) {
                this.y += this.speed;
            }

            if (this.keyH.leftPressed) {
                this.x -= this.speed;
            }

            if (this.keyH.rightPressed) {
                this.x += this.speed;
            }
        }

        // Update walking sprite when applicable
        if ((this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed) && !this.collided) {
            ++this.spriteCounter;
            if (this.spriteCounter > 7) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }

                this.spriteCounter = 0;
            }
        }

    }

    // Draw player based on spriteNum
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if (this.spriteNum == 1) {image = this.up1;}
                if (this.spriteNum == 2) {image = this.up2;}
                break;
            case "left":
                if (this.spriteNum == 1) {image = this.left1;}
                if (this.spriteNum == 2) {image = this.left2;}
                break;
            case "down":
                if (this.spriteNum == 1) {image = this.down1;}
                if (this.spriteNum == 2) {image = this.down2;}
                break;
            case "right":
                if (this.spriteNum == 1) {image = this.right1;}
                if (this.spriteNum == 2) {image = this.right2;}
        }
        g2.drawImage(image, this.x, this.y, this.panel.tileSize, this.panel.tileSize, (ImageObserver)null);
    }
}
