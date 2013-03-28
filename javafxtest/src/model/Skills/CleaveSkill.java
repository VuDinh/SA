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
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CleaveSkill extends Skill {

    public CleaveSkill(int range, String name, boolean stun, boolean slow, BufferedImage[] images, int ID, int damage,BufferedImage icon, String SE) {
        super(range, name, stun, slow, images, ID, damage, icon, SE);
    }

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

    }

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

    @Override
    public void drawPathOnHero(Graphics g, Hero hero, Cell to, int scrollX, int scrollY, GameMap panel) {
        if (status == SkillStatus.before) {
            calculatePath(hero);
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

    @Override
    public void drawSkillOnHero(Graphics g, Hero hero, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.

    }
}
