package model.HeroSystem;

import Utilities.Utilizer;
import View.Ingame.Cell;
import View.Ingame.ControlPanel;
import View.Ingame.Game;
import View.Ingame.GameMap;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/23/13
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroMoveThread extends Thread {
    Hero hero;
    Game game;
    public HeroMoveThread(Hero hero,Game game){
        this.hero = hero;
        this.game = game;
        hero.setStatus(HeroStatus.moving);
    }

    //run hero moving thread
    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        for(Iterator i=hero.getShortestPathSelect().iterator();i.hasNext();){
            Cell cell=(Cell) i.next();
            hero.moveCharacter(cell.getColPos(), cell.getRowPos());
            while(hero.getDistanceX()!=0 ||hero.getDistanceY()!=0){
                hero.move();
                hero.nextSprite();
                game.getGameMap().repaint();
                game.getControlPanel().repaint();
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            //hero.setAP(hero.getAP() - 2);
        }
        hero.setStatus(HeroStatus.standing);
        hero.calculateRange(hero.getRow(), hero.getCol(), ((int) hero.getAP() / 2) + 1);
        Utilizer.MOVEMAP[hero.getRow()][hero.getCol()]=12;
        new HeroStandThread(hero,game.getGameMap()).start();

    }
}
