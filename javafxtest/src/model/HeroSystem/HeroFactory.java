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
    public HeroFactory(){}

    //hero factory contains all heroes
    public Hero createHero(int type){
        Hero hero=null;
        skillFactory= Utilizer.factoryContext.getBean("skillFactory",SkillFactory.class);
        if(type==0){
            hero=new Hero(100,100,10,10,200,5,5, 0,"SWORDER",Utilizer.DECS_HERO1);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(6));
            hero.addSkill(skillFactory.createCleaveSkill(1));
        }
        if(type==1){
            hero=new Hero(200,200,10,10,200,5,5, 1,"LANCER",Utilizer.DECS_HERO2);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(1));
            hero.addSkill(skillFactory.createCleaveSkill(2));
        }
        if(type==2){
            hero=new Hero(150,150,10,10,200,5,5, 2,"ARCHER",Utilizer.DECS_HERO3);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(2));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        if(type==3){
            hero=new Hero(300,300,10,10,200,5,5, 3,"AXER",Utilizer.DECS_HERO4);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(4));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        if(type==4){
            hero=new Hero(200,200,10,10,200,5,5, 4,"KILLER",Utilizer.DECS_HERO5);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(5));
            hero.addSkill(skillFactory.createCleaveSkill(4));
        }
        if(type==5){
            hero=new Hero(150,150,10,10,200,5,5, 5,"HEALER",Utilizer.DECS_HERO6);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(6));
            hero.addSkill(skillFactory.createCleaveSkill(2));
        }
        if(type==6){
            hero=new Hero(150,150,10,10,200,5,5, 6,"MAGER",Utilizer.DECS_HERO7);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(7));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        return hero;
    }
}
