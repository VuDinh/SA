package model.Skills;

import Utilities.Utilizer;
import View.Ingame.Cell;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Skill implements Serializable, Cloneable {
    protected int ID, currentSprite, range;
    double multiplier;
    protected String name;
    protected boolean isStun, isSlow;
    protected ArrayList<Cell> path;
    protected SkillStatus status;
    protected int imageIndex;
    protected String SE, description;
    protected int AP;
    protected ArrayList<Cell> rangeCell = new ArrayList<Cell>();
    protected ArrayList<Cell> dmgCell = new ArrayList<Cell>();
    protected transient GameMap panel;
    protected int imgHeroIndex;

    public Skill() {
    }

    protected Skill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, double multiplier, String SE, int AP, String description) {
        this.range = range;
        this.name = name;
        isStun = stun;
        isSlow = slow;
        this.ID = ID;
        this.multiplier = multiplier;
        currentSprite = 0;
        path = new ArrayList<Cell>();
        status = SkillStatus.before;
        this.imageIndex = imageIndex;
        this.SE = SE;
        this.AP = AP;
        this.description = description;
    }

    public Skill(Skill skill) {
        this.range = skill.range;
        this.name = skill.name;
        isStun = skill.isStun;
        isSlow = skill.isSlow;
        this.ID = skill.ID;
        this.multiplier = skill.multiplier;
        currentSprite = 0;
        path = new ArrayList<Cell>();
        status = SkillStatus.before;
        this.imageIndex = skill.imageIndex;
        this.SE = skill.SE;
        this.AP = skill.AP;
        this.description = skill.description;
    }

    public String getDescription() {
        return description;
    }

    public int getDamage(Hero hero) {
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

    public void setImgHeroIndex(int imgHeroIndex) {
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

    public int getSkillIndex() {
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

    public void clonePath(ArrayList<Cell> path) {
        path = new ArrayList<Cell>();
        try {
            for (Cell cell : path) {
                path.add(cell.clone());
            }
        } catch (CloneNotSupportedException ex) {

        }

    }

    public void nextSprite() {
        if (currentSprite % 4 == 3) currentSprite -= 3;
        else currentSprite++;
    }

    public BufferedImage getCurrentSprite() {
        return Utilizer.SKILLIMAGEPACK.get(imgHeroIndex).getSprite(imageIndex)[currentSprite];
    }

    public Skill clone() throws CloneNotSupportedException {
        return (Skill) super.clone();
    }

    public void setPanel(GameMap panel) {
        this.panel = panel;
    }

    public int getCurrentSpriteIndex() {
        return currentSprite;
    }

    public abstract void drawSkill(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel);

    public abstract void drawPath(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel);

    public abstract void drawPathOnHero(Graphics g, Hero hero, Cell to, int scrollX, int scrollY, GameMap panel);

    public abstract void drawSkillOnHero(Graphics g, Hero hero, int scrollX, int scrollY, GameMap panel);

}
