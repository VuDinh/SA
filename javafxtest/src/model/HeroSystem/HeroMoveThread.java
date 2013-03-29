package model.HeroSystem;

import View.Ingame.Cell;
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
    GameMap panel;
    public HeroMoveThread(Hero hero,GameMap panel){
        this.hero = hero;
        this.panel = panel;
        hero.setStatus(HeroStatus.moving);
    }
    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        for(Iterator i=hero.getShortestPathSelect().iterator();i.hasNext();){
            Cell cell=(Cell) i.next();
            hero.moveCharacter(cell.getColPos(), cell.getRowPos());
            while(hero.getDistanceX()!=0 ||hero.getDistanceY()!=0){
                hero.move();
                hero.nextSprite();
                panel.repaint();

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        hero.setStatus(HeroStatus.standing);
        new HeroStandThread(hero,panel).start();

    }
}
