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
            hero=new Hero(100,100,10,10,5,5, Utilizer.hero1Array,Utilizer.IMG_AVATAR,"SWORDER", Utilizer.IMG_FACE1);
            hero.addSkill(skillFactory.createNormalSkill(1));
            hero.addSkill(skillFactory.createAOESkill(1));
            hero.addSkill(skillFactory.createCleaveSkill(1));
        }
        if(type==2){
            hero=new Hero(200,200,10,10,5,5, Utilizer.hero2Array,Utilizer.IMG_AVATAR,"LANCER", Utilizer.IMG_FACE1);
        }
        if(type==3){
            hero=new Hero(150,150,10,10,5,5, Utilizer.hero3Array,Utilizer.IMG_AVATAR,"ARCHER", Utilizer.IMG_FACE1);
        }
        return hero;
    }
}
