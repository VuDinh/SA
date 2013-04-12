package model.Skills;

import View.Ingame.Cell;
import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/25/13
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class NormalSkill extends Skill {
    public NormalSkill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, double multiplier
            , String SE, int AP) {
        super(range, name, stun, slow, imageIndex, ID, multiplier, SE, AP);
    }

    //calculate range of normal skill
    public void calculatePath(Hero to) {
        path.clear();
        if (to.getRow() > 0)
            if (Utilizer.MOVEMAP[to.getRow() - 1][to.getCol()] == 0) path.add(new Cell(to.getRow() - 1, to.getCol()));
        if (to.getCol() < Utilizer.MAP_COLS - 1)
            if (Utilizer.MOVEMAP[to.getRow()][to.getCol() + 1] == 0) path.add(new Cell(to.getRow(), to.getCol() + 1));
        if (to.getRow() < Utilizer.MAP_ROWS - 1)
            if (Utilizer.MOVEMAP[to.getRow() + 1][to.getCol()] == 0) path.add(new Cell(to.getRow() + 1, to.getCol()));
        if (to.getCol() > 0)
            if (Utilizer.MOVEMAP[to.getRow()][to.getCol() - 1] == 0) path.add(new Cell(to.getRow(), to.getCol() - 1));

        rangeCell = path;
    }

    //draw skill animation sprite
    @Override
    public void drawSkill(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.after && path.contains(to)) {
            g.drawImage(getCurrentSprite(),to.getColPos() * Utilizer.TILE_SIZE - scrollX, to.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
        }
    }

    @Override
    public void drawPath(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //draw path depends on hero location
    @Override
    public void drawPathOnHero(Graphics g, Hero hero, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.before) {
            calculatePath(hero);
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();
                g.clearRect(temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                g.drawImage(Utilizer.rangeArray[0], temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
                g.drawImage(Utilizer.rangeArray[Utilizer.MAP[temp.getRowPos()][temp.getColPos()] - 1], temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
            }
        }

    }

    @Override
    public void drawSkillOnHero(Graphics g, Hero hero, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
