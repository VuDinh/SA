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

    Hero hero;
    static Hero dummy = new HeroFactory().createHero(1);
    ChatPanel chatPanel;

    public static BufferedImage image;

    public ControlPanel(Hero hero){
        this.hero = hero;
        this.setPreferredSize(new Dimension(1280,150));
        chatPanel = new ChatPanel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                setLayout(new BorderLayout());
                add(chatPanel,BorderLayout.EAST);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
            //g.drawImage(Utilizer.IMG_CHAT_BACK,0,0, this);
        g.fillRect(0,0,getWidth(),getHeight());

            g.drawImage(Utilizer.IMG_MINI_MAP, 10, 10, this);
            g.drawImage(Utilizer.IMG_CONTROL1, 0,0, this);
            g.drawImage(Utilizer.IMG_CONTROL2, 275,0, this);

            g.drawImage(hero.getAvatar(), 190,50 , this);
            g.drawImage(hero.getSkill(1).getIcon(), 520, 65, this);
            g.drawImage(hero.getSkill(2).getIcon(), 635, 65, this);
            g.setColor(Color.WHITE);
            g.drawString(hero.getSkill(0).getDamage()+"",320, 75);
            g.drawString(hero.getMaxHP()+"",295, 140);
            g.drawString(hero.getMaxAP()+"",340, 140);
            g.drawString(hero.getName(), 195, 30);
            drawHPAP(g);

            repaint();

    }

    public void drawHPAP(Graphics g){
        int width = 361;
        double no1 = hero.getAP();
        double no2 = hero.getMaxAP();
        double realWidth = (double)width * (no1/no2);

        g.drawImage(Utilizer.IMG_BAR,290,2,420,21,this);
        g.drawImage(Utilizer.IMG_BAR,290,24,420,21,this);

        g.setColor(new Color(10, 160, 30));
        g.fillRect(320, 7, width, 11);
        g.setColor(new Color(20, 150, 180));
        g.fillRect(320, 29, (int)realWidth, 11);

        g.setColor(Color.white);
        g.drawString(hero.getHP()+"",470,17);
        g.drawString("/",490,17);
        g.drawString(hero.getMaxHP()+"",495,17);
        g.drawString((int)hero.getAP()+"",470,40);
        g.drawString("/",490,40);
        g.drawString(hero.getMaxAP() + "", 495, 40);

    }
    public ChatPanel getChatPanel(){
        return chatPanel;
    }
}
