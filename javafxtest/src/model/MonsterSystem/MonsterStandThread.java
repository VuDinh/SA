package model.MonsterSystem;

import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/3/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MonsterStandThread extends  Thread {
    Monster monster;
    GameMap panel;
    public MonsterStandThread(Monster monster,GameMap panel){
        this.monster = monster;
        this.panel = panel;
    }
    @Override
    public void run(){
        while(monster.getHP()>0){
            //System.out.println(monster.getHP());
            monster.nextSprite();
            panel.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(monster.getHP()<=0){
            monster.setImageIndex(6);
        }
    }
}
