package model.HeroSystem;

import Utilities.Utilizer;
import View.HeroChoosing.HeroPane;
import View.Ingame.Cell;
import View.Ingame.GameMap;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/26/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class TeleportThread extends  Thread {
    GameMap panel;
    Teleport loc;
    Teleport des;
    Hero hero;
    public TeleportThread(Hero hero, Teleport loc, Teleport des,GameMap panel){
        this.hero = hero;
        this.loc = loc;
        this.des = des;
        this.panel = panel;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        for(int i = 0 ; i < Utilizer.TELEPORT_SPRITE; i++){
                loc.nextSprite();
                panel.repaint();

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

            }
        }
        hero.setRow(des.getRow());
        hero.setCol(des.getCol());
        hero.setX(des.getCol()*Utilizer.TILE_SIZE);
        hero.setX(des.getCol()*Utilizer.TILE_SIZE);
        panel.repaint();
        //new TeleportThread(teleport,panel).start();

    }
}
