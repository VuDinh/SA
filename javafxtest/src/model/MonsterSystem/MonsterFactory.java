package model.MonsterSystem;

import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.Skills.SkillFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/18/13
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MonsterFactory {
    public Monster createMonster(int type){
        Monster monster=null;
        if(type==1){
            monster=new Monster(100,100,10,10,5,8, 0,"CERBERUS");
        }
        if(type==2){
            monster=new Monster(200,200,10,10,5,5, 0,"CHIMERA");
        }
        if(type==3){
            monster=new Monster(150,150,10,10,5,5, 0,"HARPY");
        }
        return monster;
    }
}
