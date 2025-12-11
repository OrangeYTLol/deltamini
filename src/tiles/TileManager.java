package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

  GamePanel panel;
  public Tile[] tile;
  public int mapTile[][];

  public TileManager(GamePanel panel) {
    this.panel = panel;
    tile = new Tile[14];
    mapTile = new int[panel.maxScreenColumns + 2][panel.maxScreenRows + 2];

    getTileInfo();
    loadMap("demo1");
  }

  public void getTileInfo() {
    try {
      //Define each tile type in the tile[] array
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand1.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand2.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand3.png"));

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand4.png"));

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand5.png"));

      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand6.png"));

      tile[6] = new Tile();
      tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand7.png"));

      tile[7] = new Tile();
      tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand8.png"));

      tile[8] = new Tile();
      tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand9.png"));

      tile[9] = new Tile();
      tile[9].collision = true;
      tile[9].animFrames = 3;
      tile[9].animSpeed = 30;
      tile[9].animID = "water";
      tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water1.png"));

      tile[10] = new Tile();
      tile[10].collision = true;
      tile[10].animFrames = 4;
      tile[10].animID = "tree";
      tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree1.png"));

      tile[11] = new Tile();
      tile[11].collision = true;
      tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall1.png"));

      tile[12] = new Tile();
      tile[12].levelTriggerInfo[0] = "demo2";
      tile[12].levelTriggerInfo[1] = "" + 24 * panel.scale;
      tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/levelTrigger1.png"));

      tile[13] = new Tile();
      tile[13].levelTriggerInfo[0] = "demo3";
      tile[13].levelTriggerInfo[1] = "";
      tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand5.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadMap(String map) {
    // Read a map.txt file and output tiles to the mapTiles[][] array
    try {
      InputStream is = getClass().getClassLoader().getResourceAsStream("maps/" + map + ".txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < panel.maxScreenColumns + 2 && row < panel.maxScreenRows + 2) {
        String line = br.readLine();

        while (col < panel.maxScreenColumns + 2) {
          String nums[] = line.split(" ");

          int num = Integer.parseInt(nums[col]);

          mapTile[col][row] = num;
          col++;
        }
        if (col == panel.maxScreenColumns + 2) {
          col = 0;
          row++;
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void draw(Graphics2D g2) {
    int col = 1;
    int row = 1;
    int x = 0;
    int y = 0;

    //Draw tiles
    while (col < panel.maxScreenColumns  + 1 && row < panel.maxScreenRows + 1) {

      int tileNum = mapTile[col][row];

      if (tile[tileNum].animID != null) {tile[tileNum].update(tile[tileNum].animID);}
      g2.drawImage(tile[tileNum].image, x, y, panel.tileSize, panel.tileSize, null);
      col++;
      x += panel.tileSize;

      if (col == panel.maxScreenColumns + 1) {
        col = 1;
        x = 0;
        row++;
        y += panel.tileSize;
      }
    }
  }
}
