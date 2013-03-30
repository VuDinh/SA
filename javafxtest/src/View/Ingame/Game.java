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
<<<<<<< HEAD:javafxtest/src/Ingame/Game.java
        GameMap drawP = new GameMap();
=======
        GameMap drawP = new GameMap(hero);
        Graphics g;
        ControlPanel control = new ControlPanel(hero);
>>>>>>> 02e86b0795825a1792df7a40fbb17958ab75b3c9:javafxtest/src/View/Ingame/Game.java
        TurnPanel turn = new TurnPanel();
        ControlPanel control = new ControlPanel();

        frame.add(BorderLayout.CENTER, drawP);
        frame.add(BorderLayout.NORTH, turn);
        frame.add(BorderLayout.SOUTH, control);

        frame.setSize(1280, 720);
        frame.setVisible(true);

        drawP.setFocusable(true);
        control.setFocusable(true);
        drawP.requestFocusInWindow();
        drawP.requestFocus();
        frame.setResizable(true);
    }

    public static void main(String args[]){
        Game game = new Game();
    }
}
