package model.MonsterSystem;

import model.Character;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Monster extends Character {

    protected Monster(int HP, int maxHP, int AP, int maxAP, int row, int col, BufferedImage[] image,BufferedImage avatar, String name){
        super(HP, maxHP, AP, maxAP, row, col, image,avatar,name);
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
}
