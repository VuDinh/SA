package model.Skills;

import View.Ingame.Cell;
import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/25/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CleaveSkill extends Skill implements Serializable,Cloneable {

    public CleaveSkill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, double multiplier
            , String SE, int AP, String description) {
        super(range, name, stun, slow, imageIndex, ID, multiplier, SE, AP, description);
    }
    public CleaveSkill(Skill skill){
        super(skill);
    }
/*
    public void calculatePath(Hero to) {
        path.clear();
        int count;
        if (to.getCurrentSpriteIndex() / 4 == 0) {
            count = to.getRow() + range;
            if (count > Utilizer.MAP_ROWS - 1) count = Utilizer.MAP_ROWS - 1;
            while (Utilizer.MOVEMAP[count][to.getCol()] != 0 && count > to.getRow()) {
                count--;
            }
            path.addAll(to.calculateShortestPath(new Cell(count, to.getCol())));
        } else if (to.getCurrentSpriteIndex() / 4 == 1) {
            count = to.getCol() - range;
            if (count < 0) count = 0;
            while (Utilizer.MOVEMAP[to.getRow()][count] != 0 && count < to.getCol()) {
                count++;
            }
            path.addAll(to.calculateShortestPath(new Cell(to.getRow(), count)));
        } else if (to.getCurrentSpriteIndex() / 4 == 2) {
            count = to.getCol() + range;
            if (count > Utilizer.MAP_COLS-1) count = Utilizer.MAP_COLS-1;
            while (Utilizer.MOVEMAP[to.getRow()][count] != 0 && count < to.getCol()) {
                count--;
            }
            path.addAll(to.calculateShortestPath(new Cell(to.getRow(), count)));
        } else if(to.getCurrentSpriteIndex() / 4 == 3){
            count = to.getRow() - range;
            if (count < 0) count = 0;
            while (Utilizer.MOVEMAP[count][to.getCol()] != 0 && count < to.getRow()) {
                count++;
            }
            path.addAll(to.calculateShortestPath(new Cell(count, to.getCol())));
        }
        if(!path.isEmpty())
            path.remove(0);

    }*/

    //calculate path of Cleave skill
    public void calculatePath(Hero to) {
        path.clear();
        int count;

        if (to.getCurrentSpriteIndex() / 4 == 0) {
            if(to.getRow()+range>39)path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol(),39)));
            else path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol(),to.getRow()+range)));
        } else if (to.getCurrentSpriteIndex() / 4 == 1) {
            if(to.getCol()-range<=0)path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(0,to.getRow())));
            else path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol()-range,to.getRow())));
        } else if (to.getCurrentSpriteIndex() / 4 == 2) {
            if(to.getCol()+range>39)path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(39,to.getRow())));
            else path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol()+range,to.getRow())));
        } else if(to.getCurrentSpriteIndex() / 4 == 3){
            if(to.getRow()-range<=0)path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol(),0)));
            else path.addAll(Utilizer.straightPath(new Cell(to.getCol(),to.getRow()),new Cell(to.getCol(),to.getRow()-range)));
        }
        if(!path.isEmpty())
            path.remove(0);
        rangeCell = path;
        dmgCell = path;
    }

    //draw skill animation sprite
    @Override
    public void drawSkill(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.after && path.contains(to)) {
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();
                g.drawImage(getCurrentSprite(), temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos()
                        * Utilizer.TILE_SIZE - scrollY, panel);
            }
        }
    }

    @Override
    public void drawPath(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    //draw path depends on the location of hero
    @Override
    public void drawPathOnHero(Graphics g, Hero hero, Cell to, int scrollX, int scrollY, GameMap panel) {
        if (status == SkillStatus.before) {
            calculatePath(hero);
            System.out.println(path);
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();
                g.clearRect(temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE
                        - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                g.drawImage(Utilizer.rangeArray[0], temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos()
                        * Utilizer.TILE_SIZE - scrollY, panel);
                g.drawImage(Utilizer.rangeArray[Utilizer.MAP[temp.getRowPos()][temp.getColPos()] - 1], temp.getColPos()
                        * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
            }
        }
    }

    public CleaveSkill clone() throws CloneNotSupportedException{
        return (CleaveSkill)super.clone();
    }

    @Override
    public void drawSkillOnHero(Graphics g, Hero hero, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.

    }
    /*public CleaveSkill clone() throws CloneNotSupportedException{
        return (CleaveSkill)super.clone();
    }*/
}
