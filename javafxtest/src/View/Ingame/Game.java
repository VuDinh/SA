package View.Ingame;

import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/26/13
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends JFrame {
    public Game(){
        Utilizer.load();

        HeroFactory hF = new HeroFactory();
        Hero hero = hF.createHero(1);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMap drawP = new GameMap(hero);
        Graphics g;
        ControlPanel control = new ControlPanel(hero);
        TurnPanel turn = new TurnPanel();
        frame.add(BorderLayout.CENTER, drawP);
        frame.add(BorderLayout.SOUTH, control);
        frame.add(BorderLayout.NORTH, turn);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public static void main(String args[]){
        Game game = new Game();
    }
}
