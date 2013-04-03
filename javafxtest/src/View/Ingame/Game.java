package View.Ingame;

import model.AccountSystem.Account;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;
import model.MonsterSystem.Monster;
import model.MonsterSystem.MonsterFactory;

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
    Account me;
    GameMap drawP;
    TurnPanel turn;
    ControlPanel control;
    public Game(Account me){
        this.me=me;
        HeroFactory hF = new HeroFactory();
        Hero hero = hF.createHero(1);
        MonsterFactory mF = new MonsterFactory();
        Monster monster = mF.createMonster(1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawP = new GameMap(hero,monster);
        Graphics g;
        control = new ControlPanel(hero);
        TurnPanel turn = new TurnPanel();
        this.add(BorderLayout.CENTER, drawP);
        this.add(BorderLayout.NORTH, turn);
        this.add(BorderLayout.SOUTH, control);
        this.setSize(1280, 720);

        drawP.setFocusable(true);
        control.setFocusable(true);
        drawP.requestFocusInWindow();
        drawP.requestFocus();
        this.setResizable(true);
    }

    public static void main(String args[]){
    }
    public GameMap getGameMap(){
        return drawP;
    }
    public ControlPanel getControlPanel(){
        return control;
    }
    public TurnPanel getTurnPanel(){
        return turn;
    }
    public ChatPanel getChatPanel(){
        return control.getChatPanel();
    }
    public Account getAccount(){
        return me;
    }
}
