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
    protected int ID,currentSprite,range;
    double multiplier;
    protected String name;
    protected boolean isStun,isSlow;
    protected ArrayList<Cell> path;
    protected SkillStatus status;
    protected int imageIndex;
    protected String SE;
    protected int AP;
    protected ArrayList<Cell>rangeCell = new ArrayList<Cell>();
    protected ArrayList<Cell>dmgCell = new ArrayList<Cell>();
    protected GameMap panel;
    protected int imgHeroIndex;
    public Skill() {
    }
    protected Skill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, double multiplier, String SE, int AP) {
        this.range = range;
        this.name = name;
        isStun = stun;
        isSlow = slow;
        this.ID = ID;
        this.multiplier = multiplier;
        currentSprite = 0;
        path=new ArrayList<Cell>();
        status = SkillStatus.before;
        this.imageIndex = imageIndex;
        this.SE = SE;
        this.AP = AP;
    }

    public int getDamage(Hero hero){
        return (int) Math.round(hero.getAttk() * multiplier);
    }

    public ArrayList<Cell> getRangeCell() {
        return rangeCell;
    }

    public ArrayList<Cell> getDmgCell() {
        return dmgCell;
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

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
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
        System.out.println(Utilizer.SKILLIMAGEPACK.get(imgHeroIndex));
        return Utilizer.SKILLIMAGEPACK.get(imgHeroIndex).getIcon(imageIndex);
    }
    public int getSkillIndex(){
        return imageIndex;
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
