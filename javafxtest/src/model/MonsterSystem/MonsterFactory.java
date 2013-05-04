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

    //monster factory contains all monsters
    public Monster createMonster(int type){
        Monster monster=null;
        if(type==1){
            monster=new Monster(1000,1000,10,10,100,5,8, 8,"CERBERUS","A 3-Head Dog");
        }
        if(type==2){
            monster=new Monster(500,500,10,10,150,5,5, 9,"CHIMERA","A mixed between different Animals");
        }
        if(type==3){
            monster=new Monster(1000,1000,10,10,50,5,5, 10,"HARPY","Half Human Half Bird");
        }
        return monster;
    }
}
