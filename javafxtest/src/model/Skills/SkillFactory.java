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
            skill = new AOESkill(4, "Storm", false, false, 1, 1, 200, Utilizer.SOUND_FIRE, 3);
        } else if (type == 2) {
            skill = new AOESkill(2, "Fire", false, false, 2, 1, 200, Utilizer.SOUND_FIRE, 3);
        } else if (type == 3) {
            skill = new AOESkill(2, "Ice", false, false, 3, 1, 200, Utilizer.SOUND_FIRE, 3);
        }
        return skill;
    }
    public NormalSkill createNormalSkill(int type){
        NormalSkill skill=null;
        if(type==1){
            skill=new NormalSkill(4, "Normal Slice", false, false, 0, 1, 200, Utilizer.SOUND_ATTACK, 2);
        }
        return skill;
    }
    public CleaveSkill createCleaveSkill(int type){
        CleaveSkill skill=null;
        if(type==1){
            skill=new CleaveSkill(4, "Thunder Fall", false, false, 2, 1, 200, Utilizer.SOUND_THUNDER, 4);
        }
        return skill;
    }
}
