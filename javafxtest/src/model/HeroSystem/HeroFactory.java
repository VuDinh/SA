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
        if(type==1){
            hero=new Hero(100,100,10,10,200,5,5, 0,"SWORDER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(6));
            hero.addSkill(skillFactory.createCleaveSkill(1));
        }
        if(type==2){
            hero=new Hero(200,200,10,10,200,5,5, 1,"LANCER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(3));
            hero.addSkill(skillFactory.createCleaveSkill(2));
        }
        if(type==3){
            hero=new Hero(150,150,10,10,200,5,5, 2,"ARCHER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(3));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        if(type==4){
            hero=new Hero(300,150,10,10,200,5,5, 3,"AXER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(4));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        if(type==5){
            hero=new Hero(200,150,10,10,200,5,5, 4,"KILLER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(5));
            hero.addSkill(skillFactory.createCleaveSkill(4));
        }
        if(type==6){
            hero=new Hero(150,150,10,10,200,5,5, 5,"HEALER","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(6));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        if(type==7){
            hero=new Hero(150,150,10,10,200,5,5, 6,"MAGE","");
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(7));
            hero.addSkill(skillFactory.createCleaveSkill(3));
        }
        return hero;
    }
}
