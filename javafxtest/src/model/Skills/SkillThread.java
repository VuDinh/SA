package model.Skills;

import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.HeroStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/25/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillThread extends Thread {
    private GameMap panel;
    private Skill skill;
    public SkillThread(GameMap panel,Skill skill){
        this.skill = skill;
        this.panel=panel;
    }
    public void run(){
        Utilizer.playWAV(skill.getSE(),0);
        for(int i=0;i< Utilizer.SKILL_SIZE;i++){
            skill.nextSprite();
            panel.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        skill.setStatus(SkillStatus.none);
        panel.getHero().setStatus(HeroStatus.standing);
        panel.repaint();
    }
}
