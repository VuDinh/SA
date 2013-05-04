package model.MonsterSystem;

import model.Character;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Monster extends Character implements Cloneable,Serializable {

    protected Monster(int HP, int maxHP, int AP, int maxAP, int Attk, int row, int col,int imageIndex,String name, String description){
        super(HP, maxHP, AP, maxAP, Attk, row, col, imageIndex,name,description);
    }
    @Override
    public void draw(Graphics g, int scrollX, int scrollY) {
        //To change body of implemented methods use File | Settings | File Templates.
        g.drawImage(getCurrentSprite(),getX()-scrollX,getY()-scrollY,getPanel());
    }

    @Override
    public void nextSprite() {
        //To change body of implemented methods use File | Settings | File Templates.
        if(currentSprite % 4 == 3) currentSprite-=3;
        else currentSprite++;
    }

    @Override
    public void moveCharacter(int col, int row) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void move() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resetPath() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    public Monster clone() throws CloneNotSupportedException{
        return (Monster)super.clone();
    }
}
