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

    public SkillFactory() {

    }

    public AOESkill createAOESkill(int type) {
        AOESkill skill = null;
        if (type == 1) {
            skill = new AOESkill(4, "Storm", false, false, Utilizer.thunderSkillArray, 1, 200, Utilizer.IMG_SKILL1, Utilizer.SOUND_FIRE);
        } else if (type == 2) {
            skill = new AOESkill(2, "Fire", false, false, Utilizer.fireSkillArray, 1, 200, Utilizer.IMG_SKILL2, Utilizer.SOUND_FIRE);
        } else if (type == 3) {
            skill = new AOESkill(2, "Ice", false, false, Utilizer.fireSkillArray, 1, 200, Utilizer.IMG_SKILL2, Utilizer.SOUND_FIRE);
        }
        return skill;
    }
    public NormalSkill createNormalSkill(int type){
        NormalSkill skill=null;
        if(type==1){
            skill=new NormalSkill(4, "Normal Slice", false, false, Utilizer.defaultSkillArray, 1, 200, Utilizer.IMG_SKILL1, Utilizer.SOUND_ATTACK);
        }
        return skill;
    }
    public CleaveSkill createCleaveSkill(int type){
        CleaveSkill skill=null;
        if(type==1){
            skill=new CleaveSkill(4, "Light Wave", false, false, Utilizer.thunderSkillArray, 1, 200, Utilizer.IMG_SKILL2, Utilizer.SOUND_THUNDER);
        }
        return skill;
    }
}
