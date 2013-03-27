package model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Attribute {
    private int bonusMaxHP,bonusMaxAP,bonusDamage;

    public Attribute() {
        bonusMaxHP = 0;
        bonusMaxAP = 0;
        bonusDamage = 0;
    }

    public Attribute(int bonusMaxHP, int bonusMaxAP, int bonusDamage) {
        this.bonusMaxHP = bonusMaxHP;
        this.bonusMaxAP = bonusMaxAP;
        this.bonusDamage = bonusDamage;
    }

    public int getBonusMaxHP() {
        return bonusMaxHP;
    }

    public void setBonusMaxHP(int bonusMaxHP) {
        this.bonusMaxHP = bonusMaxHP;
    }

    public int getBonusMaxAP() {
        return bonusMaxAP;
    }

    public void setBonusMaxAP(int bonusMaxAP) {
        this.bonusMaxAP = bonusMaxAP;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }
}
