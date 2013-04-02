package model.HeroSystem;

import View.Ingame.GameMap;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/29/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroAttackThread extends Thread {
    Hero hero;
    GameMap panel;

    public HeroAttackThread(Hero hero, GameMap panel){
        this.hero = hero;
        this.panel = panel;
    }

    public void run(){
        attack();
        if(hero.getStatus().equals(HeroStatus.attacking)){
            for(int i =0; i<4; i++){
            hero.nextSprite();
            panel.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            }
        reset();
        }
    }

    public void attack(){
        if(panel.getSelectedCell().getColPos() < hero.getCol())
            hero.setCurrentSprite(16);
        else hero.setCurrentSprite(20);
    }

    public void reset(){
        if(hero.getCurrentSpriteIndex()==16)
            hero.setCurrentSprite(4);
        else hero.setCurrentSprite(8);
    }
}