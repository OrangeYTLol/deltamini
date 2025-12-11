package main;
// #5 11:22
/* TODO:
  - implement collision on all 4 faces - mostly done
  - make level triggers - mostly done
  - add some kind of object/entity
  - redo the map/tile system
 */
import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    //Initialize everything
    JFrame window = new JFrame("Game");
    GamePanel panel = new GamePanel();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
    window.setLocation(0, 0);
    window.setResizable(false);
    window.add(panel);

    window.pack();

    panel.startGameThread();
  }
}
