package model;

import Ingame.GameMap;
import Utilities.Utilizer;
import model.ItemSystem.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Character {
    protected int HP,maxHP,AP,maxAP,row,col,x,y,distanceX,distanceY;
    protected BufferedImage[] image;
    protected int dirX,dirY,currentSprite,currentMove;
    protected GameMap panel;
    protected boolean isChosen;
    protected BufferedImage avatar;
    protected String name;
    protected  Character() {
    }

    protected Character(int HP, int maxHP, int AP, int maxAP, int row, int col, BufferedImage[] image,BufferedImage avatar,String name) {
        this.HP = HP;
        this.maxHP = maxHP;
        this.AP = AP;
        this.maxAP = maxAP;
        this.row = row;
        this.col = col;
        x= Utilizer.TILE_SIZE*row;
        y=Utilizer.TILE_SIZE*col;
        this.image = image;
        isChosen=false;
        dirX=0;
        dirY=0;
        currentSprite = 0;
        this.name=name;
        this.avatar=avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public BufferedImage getCurrentSprite() {
        return image[currentSprite];
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }
    public int getCurrentSpriteIndex(){
        return currentSprite;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public GameMap getPanel() {
        return panel;
    }

    public void setPanel(GameMap panel) {
        this.panel = panel;
    }

    public boolean getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean chosen) {
        isChosen = chosen;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getMaxAP() {
        return maxAP;
    }

    public void setMaxAP(int maxAP) {
        this.maxAP = maxAP;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage[] getImage() {
        return image;
    }

    public void setImage(BufferedImage[] image) {
        this.image = image;
    }

    public int getDistanceX() {
        return distanceX;
    }

    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;
    }

    public int getDistanceY() {
        return distanceY;
    }

    public void setDistanceY(int distanceY) {
        this.distanceY = distanceY;
    }

    public abstract void draw(Graphics g,int scrollX,int scrollY);
    public abstract void nextSprite();
    public abstract void moveCharacter(int col,int row);
    public abstract void move();
    public abstract void resetPath();

}