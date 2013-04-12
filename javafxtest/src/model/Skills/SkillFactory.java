package model.Skills;

import Utilities.Utilizer;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/24/13
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillFactory {
    private static final int AOE_SKILL = 1;

    public SkillFactory() {}

    //creates all skills
    public AOESkill createAOESkill(int type) {
        AOESkill skill = null;
        if (type == 1) {
            skill = new AOESkill(3, "Fire", false, false, 1, 1, 1.5, Utilizer.SOUND_FIRE, 3, 1, false);
        } else if (type == 2) {
            skill = new AOESkill(3, "Thunder", false, false, 2, 2, 1.5, Utilizer.SOUND_THUNDER, 3, 1, false);
        } else if (type == 3) {
            skill = new AOESkill(3, "Wind", false, false, 3, 3, 1.5, Utilizer.SOUND_WIND, 3, 1, false);
        }else if (type == 4) {
            skill = new AOESkill(3, "Earth", false, false, 4, 4, 1.5, Utilizer.SOUND_EARTH, 3, 1, false);
        }else if (type == 5) {
            skill = new AOESkill(3, "Ice", false, false, 5, 5, 1.5, Utilizer.SOUND_ICE, 3, 1, false);
        } else if (type == 6) {
            skill = new AOESkill(4, "Explosion", false, false, 9, 6, 1.5, Utilizer.SOUND_EXPLODE, 3, 1, true);
        }else if (type == 7) {
            skill = new AOESkill(4, "Blizzard", false, false, 10, 7, 1.5, Utilizer.SOUND_BLIZZARD, 3, 1, true);
        }else if (type == 8) {
            skill = new AOESkill(4, "Spark", false, false, 11, 8, 1.5, Utilizer.SOUND_SPARK, 3, 1, false);
        }
        return skill;
    }
    public NormalSkill createNormalSkill(int type){
        NormalSkill skill=null;
        if(type==1){
            skill=new NormalSkill(4, "Normal Slice", false, false, 0, 1, 1, Utilizer.SOUND_ATTACK, 2);
        }
        return skill;
    }
    public CleaveSkill createCleaveSkill(int type){
        CleaveSkill skill=null;
        if(type==1){
            skill=new CleaveSkill(4, "Thunder Fall", false, false, 2, 9, 1.5, Utilizer.SOUND_THUNDER, 4);
        } else if(type==2){
            skill=new CleaveSkill(3, "Fire Strike", false, false, 6, 10, 1.5, Utilizer.SOUND_FIRESTRIKE, 4);
        } else if(type==3){
            skill=new CleaveSkill(3, "Ice Strike", false, false, 7, 11, 1.5, Utilizer.SOUND_ICESTRIKE, 4);
        }else if(type==4){
            skill=new CleaveSkill(3, "Thunder Strike", false, false, 8, 12, 1.5, Utilizer.SOUND_THUNDERSTRIKE, 4);
        }
        return skill;
    }
}
