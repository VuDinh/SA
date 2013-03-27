package model.HeroSystem;

import Ingame.GameMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/23/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroStandThread extends Thread {
    Hero hero;
    GameMap panel;
    public HeroStandThread(Hero hero,GameMap panel){
        this.hero = hero;
        this.panel = panel;
    }
    @Override
    public void run(){
        while(hero.getStatus().equals(HeroStatus.standing)){
            hero.nextSprite();
            panel.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
