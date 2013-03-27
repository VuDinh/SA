import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/5/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class APanel extends JPanel {
    static ArrayList<BufferedImage> spriteListTerrain = new ArrayList<BufferedImage>();
    static int width = 32;
    static int height = 32;

    public ArrayList<BufferedImage> spriteTerrainSetup() //reads in image file and divides it into the sprites
    {

        {
            try {
                //3 kinds of tiles
                BufferedImage bigImg = ImageIO.read(new File("tile.png"));
                //BufferedImage bigImg = ImageIO.read(new File("tileselected.png"));
                //BufferedImage bigImg = ImageIO.read(new File("tilerange.png"));

                {
                    final int mapX = 32;
                    final int mapY = 32;
                    int rows = 18;
                    int cols = 8;

                    BufferedImage[][] sprites = new BufferedImage[cols][rows];

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            sprites[j][i] = bigImg.getSubimage(j * width, i * height, width, height);

                        }
                    }
                    for (int i = 0; i < mapX; i++) {
                        for (int j = 0; j < mapY; j++) {
                            //spriteListTerrain
                            BufferedImage grass = sprites[0][0];
                            spriteListTerrain.add(grass);
                            BufferedImage trees = sprites[4][1];
                            spriteListTerrain.add(trees);
                            BufferedImage firewood = sprites[5][3];
                            spriteListTerrain.add(firewood);
                        }
                    }
                    System.out.println(spriteListTerrain.size());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spriteListTerrain;
    }

    public void paintComponent(Graphics g) //paints the sprites to the screen based off of the list created below
    {
        Random rand = new Random();
        super.paintComponent(g);
        int cols = 18;
        int rows = 18;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                g.drawImage(spriteListTerrain.get(rand.nextInt(3)), i * width, j * height, (ImageObserver) this);
            }
        }
    }
}
