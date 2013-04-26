package model.HeroSystem;

import Utilities.Utilizer;
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
    Teleport teleport;
    public TeleportThread(Teleport teleport,GameMap panel){
        this.teleport = teleport;
        this.panel = panel;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        for(int i = 0 ; i < Utilizer.TELEPORT_SPRITE; i++){
                teleport.nextSprite();
                panel.repaint();

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

            }
        }
        new TeleportThread(teleport,panel).start();

    }
}
