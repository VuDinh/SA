package View.Ingame;

import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/22/13
 * Time: 8:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ControlPanel extends JPanel {
    static JPanel miniMap = null;
    static JPanel avatar = null;
    static JPanel stats = null;

    static int x, y;
    static Hero hero = null;
    static Hero dummy = new HeroFactory().createHero(1);
    ChatPanel chatPanel;

    public static BufferedImage image;

    public ControlPanel(){
        this.setPreferredSize(new Dimension(1280,150));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                chatPanel = new ChatPanel();
                setLayout(new BorderLayout());
                add(chatPanel,BorderLayout.EAST);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        hero = dummy;
            g.fillRect(0,0,getWidth(),getHeight());

            g.drawImage(Utilizer.IMG_MINI_MAP, 10, 10, this);
            g.drawImage(Utilizer.IMG_CONTROL1, 0,0, this);
            g.drawImage(Utilizer.IMG_CONTROL2, 275,0, this);

            g.drawImage(hero.getAvatar(), 190,50 , this);
            g.drawImage(hero.getSkill(0).getIcon(), 530, 73, this);
            g.drawImage(hero.getSkill(1).getIcon(), 645, 73, this);
            g.setColor(Color.WHITE);
            g.drawString(hero.getSkill(0).getDamage()+"",320, 75);
            g.drawString(hero.getHP()+"",295, 140);
            g.drawString(hero.getAP()+"",340, 140);
            g.drawString(hero.getName(), 195, 30);
            drawHPAP(g);

            repaint();

    }

    public void drawHPAP(Graphics g){

        g.setColor(new Color(10,160,30));
        g.fillRect(290,2,420,25);
        g.setColor(new Color(20,150,180));
        g.fillRect(290,28,420,15);
        g.setColor(Color.lightGray);
        g.drawRect(290, 2, 420, 25);
        g.drawRect(290, 28, 420, 15);
        g.setColor(Color.white);
        g.drawString(hero.getHP()+"",470,17);
        g.drawString("/",490,17);
        g.drawString(hero.getMaxHP()+"",495,17);
        g.drawString(hero.getAP()+"",470,42);
        g.drawString("/",490,42);
        g.drawString(hero.getMaxAP()+"",495,42);

    }
                    public static void main(String args[]){
                        JFrame frame = new JFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        ControlPanel control = new ControlPanel();
                        frame.add(control);
                        frame.setSize(new Dimension(1280,180));
                        frame.setVisible(true);
                        frame.setResizable(false);
                    }
}
