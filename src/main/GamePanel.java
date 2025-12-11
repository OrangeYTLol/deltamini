package main;
import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
  // Initialize window properties
  final int originalTileSize = 16;
  public int scale = 6;
  public final int tileSize = originalTileSize * scale;
  public final int maxScreenColumns = 12;
  public final int maxScreenRows = 8;
  public final int screenWidth = maxScreenColumns * tileSize;
  public final int screenHeight = maxScreenRows * tileSize;

  int FPS = 30;

  // Create objects
  public TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  public CollisionHandler collisionH = new CollisionHandler(this);
  public Player player1 = new Player(this, keyH);

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

 // Start game thread loop
  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // Create FPS cap with delta time
  @Override
  public void run() {

    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    long drawCount = 0;

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
        System.out.println("FPS: " + drawCount);
        //System.out.println(getWidth());
        drawCount = 0;
        timer = 0;
      }
    }
  }

  // Updates objects before drawing to the screen
  public void update() {
    player1.update();
  }

  // Draws objects to the screen
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    tileM.draw(g2);
    player1.draw(g2);
    g2.dispose();
  }
}
