package View.Ingame;

import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.Character;
import model.HeroSystem.HeroFactory;
import model.MonsterSystem.Monster;
import model.MonsterSystem.Tower;

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

    Character character;
    ChatPanel chatPanel;
    private Cell clickedCell;

    public static BufferedImage image;

    public ControlPanel(Character hero) {
        this.character = hero;
        this.setPreferredSize(new Dimension(1280, 150));
        chatPanel = new ChatPanel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLayout(new BorderLayout());
                add(chatPanel, BorderLayout.EAST);
            }
        });

    }

    public void setCharacter(Character hero) {
        this.character = hero;
        repaint();
    }
    public void setClickedCell(Cell cell){
        clickedCell=cell;
    }
    public Cell getClickedCell(){
        return clickedCell;
    }

    //paint components and hero's stat
    @Override
    public void paintComponent(Graphics g) {
        //g.drawImage(Utilizer.IMG_CHAT_BACK,0,0, this);

        g.setFont(Utilizer.FONT);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.clearRect(10, 10, 130, 130);
        g.drawImage(Utilizer.IMG_MINI_MAP, 10, 10, this);
        if(clickedCell!=null){
            g.drawRect(clickedCell.getX(),clickedCell.getY(),30,15);
        }
        g.drawImage(Utilizer.IMG_CONTROL1, 0, 0, this);
        g.drawImage(Utilizer.IMG_CONTROL2, 275, 0, this);
        Hero hero = null;
        Monster monster=null;
        if (character instanceof Hero) {
            hero = (Hero) character;
            g.drawImage(hero.getAvatar(), 190, 50, this);
            g.drawImage(hero.getSkill(1).getIcon(), 520, 65, this);
            g.drawImage(hero.getSkill(2).getIcon(), 635, 65, this);
            g.setColor(Color.WHITE);
            g.drawString(hero.getAttk() + "", 320, 75);
            g.drawString(hero.getMaxHP() + "", 295, 140);
            g.drawString(hero.getMaxAP() + "", 340, 140);
            g.drawString(hero.getName(), 195, 30);
        }
        else if(character instanceof Monster || character instanceof  Tower){
            //monster=(Monster) character;

            g.drawImage(character.getAvatar(), 190, 50, this);
            g.setColor(Color.WHITE);
            g.drawString(character.getAttk() + "", 320, 75);
            g.drawString(character.getMaxHP() + "", 295, 140);
            g.drawString(character.getMaxAP() + "", 340, 140);
            g.drawString(character.getName(), 195, 30);
        }
//        else if (character instanceof Tower){
//            Tower tower = (Tower) character;
//            g.drawImage(monster.getAvatar(), 190, 50, this);
//            g.setColor(Color.WHITE);
//            g.drawString(monster.getAttk() + "", 320, 75);
//            g.drawString(monster.getMaxHP() + "", 295, 140);
//            g.drawString(monster.getMaxAP() + "", 340, 140);
//            g.drawString(monster.getName(), 195, 30);
//        }
        drawHPAP(g);

        repaint();

    }

    //draw HP, AP bar
    public void drawHPAP(Graphics g) {
        int width = 361;
        double no1 = character.getAP();
        double no2 = character.getMaxAP();
        double aPWidth = (double) width * (no1 / no2);
        double hPWidth = (double) width * ((double)character.getHP() / character.getMaxHP());
        g.setFont(Utilizer.FONT);
        g.drawImage(Utilizer.IMG_BAR, 290, 2, 420, 21, this);
        g.drawImage(Utilizer.IMG_BAR, 290, 24, 420, 21, this);

        g.setColor(new Color(10, 160, 30));
        g.fillRect(320, 7, (int) hPWidth, 11);
        g.setColor(new Color(20, 150, 180));
        g.fillRect(320, 29, (int) aPWidth, 11);

        g.setColor(Color.white);
        g.drawString(character.getHP() + "", 470, 17);
        g.drawString("/", 490, 17);
        g.drawString(character.getMaxHP() + "", 495, 17);
        g.drawString((int) character.getAP() + "", 470, 40);
        g.drawString("/", 490, 40);
        g.drawString(character.getMaxAP() + "", 495, 40);

    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}
