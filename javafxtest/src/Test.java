/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/5/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.*;

public class Test {
//    static BufferedImage bigImg ;
//// The above line throws an checked IOException which must be caught.
//
//    final static int width = 32;
//    final static int height = 32;
//    final static int rows = 19;
//    final static int cols = 8;
//    static BufferedImage[] sprites = new BufferedImage[rows * cols];
//    public static void main(String args[]){
//        try {
//            bigImg = ImageIO.read(new File("tile.png"));
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        for(int i= 0; i < rows ; i++)
//    {
//        for (int j = 0; j < cols; j++)
//        {
//            sprites[(i * cols) + j] = bigImg.getSubimage(
//                    j * width,
//                    i * height,
//                    width,
//                    height
//            );
//        }
//    }

    public static void main(String args[]){

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            APanel drawP = new APanel();
            drawP.spriteTerrainSetup();

            JScrollPane scroller = new JScrollPane();
            scroller.setViewportView(drawP);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

            frame.getContentPane().add(BorderLayout.CENTER, scroller);
            frame.setSize(700,700);
            frame.setVisible(true);
            frame.setResizable(true);


    }
}
