package model.HeroSystem;

import View.Ingame.GameMap;

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

    //run hero standing thread
    @Override
    public void run(){
        if(hero.getHP()<=0){
            hero.setImageIndex(11);
            panel.repaint();
        }
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
