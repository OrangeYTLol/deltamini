package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

  /* public Tile(BufferedImage image, boolean collision, ) {

  } */
  // Declare tile properties
  public BufferedImage image;
  public boolean collision = false;
  public String[] levelTriggerInfo = new String[3];
  public int animFrames;
  public int animSpeed = 100;
  public String animID;

  int curFrame = 1;
  int curTile = 1;

  // Update animation if applicable
  public void update(String animID) {
    if (curFrame >= animSpeed) {
      try {
      image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/" + animID + curTile + ".png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (curTile >= animFrames) {
        curTile = 1;
      } else {
        curTile++;
      }
      curFrame = 0;
    } else {
      curFrame++;
      //System.out.println(curFrame);
    }
  }
}
