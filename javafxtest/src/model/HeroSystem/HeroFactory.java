package model.HeroSystem;

import Utilities.Utilizer;
import model.Skills.SkillFactory;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/18/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroFactory {
    SkillFactory skillFactory;
    public HeroFactory(){

    }
    public Hero createHero(int type){
        Hero hero=null;
        skillFactory= Utilizer.factoryContext.getBean("skillFactory",SkillFactory.class);
        if(type==1){
            hero=new Hero(100,100,10,10,5,5, 0,"SWORDER");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(1));
            hero.addSkill(skillFactory.createCleaveSkill(1));
        }
        if(type==2){
            hero=new Hero(200,200,10,10,5,5, 1,"LANCER");
        }
        if(type==3){
            hero=new Hero(150,150,10,10,5,5, 2,"ARCHER");
        }
        return hero;
    }
}
