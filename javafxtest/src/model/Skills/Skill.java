package model.Skills;

import Utilities.Utilizer;
import View.Ingame.Cell;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Skill {
    protected int ID,damage,currentSprite,range;
    protected String name;
    protected boolean isStun,isSlow;
    protected ArrayList<Cell> path;
    protected SkillStatus status;
    protected int imageIndex;
    protected String SE;
    protected int AP;
    protected ArrayList<Cell>rangeCell = new ArrayList<Cell>();
    protected GameMap panel;
    protected int imgHeroIndex;
    public Skill() {
    }
    protected Skill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, int damage, String SE, int AP) {
        this.range = range;
        this.name = name;
        isStun = stun;
        isSlow = slow;
        this.ID = ID;
        this.damage = damage;
        currentSprite = 0;
        path=new ArrayList<Cell>();
        status = SkillStatus.before;
        this.imageIndex = imageIndex;
        this.SE = SE;
        this.AP = AP;
    }

    public ArrayList<Cell> getRangeCell() {
        return rangeCell;
    }

    public int getRange() {
        return range;
    }

    public int getAP() {
        return AP;
    }

    public String getSE() {
        return SE;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setImgHeroIndex(int imgHeroIndex){
        this.imgHeroIndex = imgHeroIndex;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getIcon() {
        return Utilizer.SKILLIMAGEPACK.get(imgHeroIndex).getIcon(imageIndex);
    }

    public boolean isStun() {
        return isStun;
    }

    public void setStun(boolean stun) {
        isStun = stun;
    }

    public boolean isSlow() {
        return isSlow;
    }

    public void setSlow(boolean slow) {
        isSlow = slow;
    }

    public SkillStatus getStatus() {
        return status;
    }

    public void setStatus(SkillStatus status) {
        this.status = status;
    }

    public ArrayList<Cell> getPath() {
        return path;
    }

    public void setPath(ArrayList<Cell> path) {
        this.path = path;
    }

    public void nextSprite(){
        if(currentSprite % 4 == 3) currentSprite-=3;
        else currentSprite++;
    }
    public BufferedImage getCurrentSprite(){
        return Utilizer.SKILLIMAGEPACK.get(imgHeroIndex).getSprite(imageIndex)[currentSprite];
    }
    public abstract void drawSkill(Graphics g,Cell to, int scrollX, int scrollY, GameMap panel);
    public abstract void drawPath(Graphics g,Cell to,int scrollX,int scrollY,GameMap panel);
    public abstract void drawPathOnHero(Graphics g,Hero hero,Cell to,int scrollX,int scrollY,GameMap panel);
    public abstract void drawSkillOnHero(Graphics g,Hero hero,int scrollX,int scrollY,GameMap panel);

}
