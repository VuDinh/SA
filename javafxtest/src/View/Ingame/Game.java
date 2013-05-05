package View.Ingame;

import Controllers.Requests.HeroMoveRequest;
import Utilities.Utilizer;
import model.AccountSystem.Account;
import model.Animations.HeroAnimation;
import model.Facade.Facade;
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
    private Facade me;
    private GameMap drawP;
    private TurnPanel turn;
    private ControlPanel control;
    public Game(Facade me){
        this.me=me;
        HeroFactory hF = new HeroFactory();
        Hero hero = hF.createHero(7);
        MonsterFactory mF = new MonsterFactory();
        Monster monster = mF.createMonster(1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawP = new GameMap(hero,monster);
        Graphics g;
        control = new ControlPanel(hero);
        turn = new TurnPanel();
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
    public Facade getFacade(){
        return me;
    }
    public void setInitialProperties(){
        drawP.setFacade(me);
        turn.setFacade(me);
        control.setCharacter(me.getMainHero());
        turn.setStatusMessage(me.getMatch().getTurnIndex());
        me.getGame().resetMoveMap();
        //set MoveMap base on the position

    }
    public void handleHeroMoveRequest(HeroMoveRequest request) {
        Hero temp = me.getHeroBySlotIndex(request.getSlotIndex());
        Utilizer.MOVEMAP[temp.getRow()][temp.getCol()] = 0;
        temp.setShortestPathSelect(request.getHero().getShortestPathSelect());
        HeroAnimation.move(temp, this);
    }
}
