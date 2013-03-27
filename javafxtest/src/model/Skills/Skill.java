package model.Skills;

import Ingame.Cell;
import Ingame.GameMap;
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
    protected BufferedImage[] images;
    protected ArrayList<Cell> path;
    protected SkillStatus status;
    protected BufferedImage icon;
    public Skill() {
    }
    protected Skill(int range, String name, boolean stun, boolean slow, BufferedImage[] images, int ID, int damage,BufferedImage icon) {
        this.range = range;
        this.name = name;
        isStun = stun;
        isSlow = slow;
        this.images = images;
        this.ID = ID;
        this.damage = damage;
        currentSprite = 0;
        path=new ArrayList<Cell>();
        status = SkillStatus.before;
        this.icon=icon;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
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

    public void nextSprite(){
        if(currentSprite % 4 == 3) currentSprite-=3;
        else currentSprite++;
    }
    public BufferedImage getCurrentSprite(){
        return images[currentSprite];
    }
    public abstract void drawSkill(Graphics g,Cell to, int scrollX, int scrollY, GameMap panel);
    public abstract void drawPath(Graphics g,Cell to,int scrollX,int scrollY,GameMap panel);
    public abstract void drawPathOnHero(Graphics g,Hero hero,Cell to,int scrollX,int scrollY,GameMap panel);
    public abstract void drawSkillOnHero(Graphics g,Hero hero,int scrollX,int scrollY,GameMap panel);
}
