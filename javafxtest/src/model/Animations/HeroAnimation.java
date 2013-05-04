package model.Animations;

import Utilities.Utilizer;
import View.Ingame.GameMap;
import model.HeroSystem.*;
import model.Skills.SkillStatus;
import model.Skills.SkillThread;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/5/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroAnimation {
    public static void stand(Hero hero,GameMap panel){
        HeroStandThread t=new HeroStandThread(hero,panel);
        t.start();
    }
    public static void attack(Hero hero,GameMap panel){
        hero.getCurrentSkill().setStatus(SkillStatus.after);
        Utilizer.playWAV(hero.getCurrentSkill().getSE(), 0);
        panel.getHero().clearRange();
        SkillThread t=new SkillThread(panel,hero);
        HeroAttackThread t2 = new HeroAttackThread(hero,panel);
        t.start();
        t2.start();
        hero.setAP(hero.getAP()-hero.getCurrentSkill().getAP());
    }
    public static void move(Hero hero,GameMap panel){
        hero.clearRange();
        hero.setStatus(HeroStatus.moving);
        //hero.setShortestPathSelect(hero.getShortestpathHover());
        HeroMoveThread t=new HeroMoveThread(hero, panel);
        t.start();
    }

}
