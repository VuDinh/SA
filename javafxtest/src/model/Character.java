package model;

import View.Ingame.GameMap;
import Utilities.Utilizer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Character implements Serializable {
    protected int HP, maxHP, maxAP, row, col, x, y, distanceX, distanceY, Attk;
    protected double AP;
    protected int dirX, dirY, currentSprite, currentMove;
    protected transient GameMap panel;
    protected boolean isChosen;
    protected int imageIndex;
    protected String name, description;

    protected Character() {
    }

    protected Character(int HP, int maxHP, int AP, int maxAP, int Attk, int row, int col, int imageIndex, String name, String description) {
        this.HP = HP;
        this.maxHP = maxHP;
        this.AP = AP;
        this.maxAP = maxAP;
        this.row = row;
        this.col = col;
        x = Utilizer.TILE_SIZE * row;
        y = Utilizer.TILE_SIZE * col;
        isChosen = false;
        dirX = 0;
        dirY = 0;
        currentSprite = 0;
        this.name = name;
        this.imageIndex = imageIndex;
        this.Attk = Attk;
        this.description = description;
    }

    public Character(Character character) {
        this.HP = character.HP;
        this.maxHP = character.maxHP;
        this.AP = character.AP;
        this.maxAP = character.maxAP;
        this.row = character.row;
        this.col = character.col;
        x = character.x;
        y = character.y;
        isChosen = false;
        dirX = 0;
        dirY = 0;
        currentSprite = 0;
        this.name = character.name;
        this.imageIndex = character.imageIndex;
        this.Attk = character.Attk;
        this.description = character.description;
    }

    public void setAttk(int attk) {
        Attk = attk;
    }

    public int getAttk() {
        return Attk;
    }

    public String getDescription() {
        return description;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getAvatar() {
        return Utilizer.HEROIMAGEPACK.get(imageIndex).getAvatar();
    }

    public BufferedImage getChoosing() {
        return Utilizer.HEROIMAGEPACK.get(imageIndex).getChoosing();
    }

    public BufferedImage getCurrentSprite() {
        return Utilizer.HEROIMAGEPACK.get(imageIndex).getSprite()[currentSprite];
    }

    public BufferedImage getIcon() {
        return Utilizer.HEROIMAGEPACK.get(imageIndex).getIcon();
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }

    public int getCurrentSpriteIndex() {
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

    public double getAP() {
        return AP;
    }

    public void setAP(double AP) {
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

    public int getImageIndex() {
        return imageIndex;
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
        return Utilizer.HEROIMAGEPACK.get(imageIndex).getSprite();
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

    public abstract void draw(Graphics g, int scrollX, int scrollY);

    public abstract void nextSprite();

    public abstract void moveCharacter(int col, int row);

    public abstract void move();

    public abstract void resetPath();

}
