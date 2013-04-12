package model.Skills;

import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/25/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillThread extends Thread {
    GameMap panel;
    Hero hero;
    public SkillThread(GameMap panel,Hero hero){
        this.hero = hero;
        this.panel=panel;
    }

    //run skill animation
    public void run(){

        for(int i=0;i< Utilizer.SKILL_SIZE;i++){
            hero.getCurrentSkill().nextSprite();
            panel.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        hero.getCurrentSkill().setStatus(SkillStatus.none);
        hero.setStatus(HeroStatus.standing);
        panel.setDamage(0);
        panel.repaint();
    }
}
