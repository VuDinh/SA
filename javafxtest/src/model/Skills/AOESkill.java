package model.Skills;

import View.Ingame.Cell;
import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/24/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class AOESkill extends Skill {
    int aoe;
    boolean all;

    public AOESkill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, double multiplier,
                    String SE, int AP, int aoe, boolean  all) {
        super(range,name,stun,slow,imageIndex,ID,multiplier,SE,AP);
        this.aoe = aoe;
        this.all = all;
    }

    //calculate path to a specific location
    public void calculatePath(Cell to) {
        path.clear();
        int startRow, endRow, startCol, endCol;
        startRow=Math.max(0,to.getRowPos()-aoe);
        endRow=Math.min(Utilizer.MAP_ROWS-aoe,to.getRowPos()+aoe);
        startCol=Math.max(0,to.getColPos()-aoe);
        endCol=Math.min(Utilizer.MAP_COLS-aoe,to.getColPos()+aoe);
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++)
                   path.add(new Cell(i, j));
        }

        dmgCell = path;
    }

    //draw skill animation sprite
    @Override
    public void drawSkill(Graphics g,Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.after) {
            if(all == false){
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();

                g.drawImage(getCurrentSprite(),temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
            }
            }else{
                int col = panel.getSelectedCell().getColPos()-aoe;
                int row = panel.getSelectedCell().getRowPos()-aoe;
                g.drawImage(getCurrentSprite(), col * Utilizer.TILE_SIZE - scrollX, row * Utilizer.TILE_SIZE - scrollY,panel);
            }
        }
    }

    //draw skill AOE
    @Override
    public void drawPath(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.before || status==SkillStatus.none) {
            calculatePath(to);
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();
                g.clearRect(temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                g.drawImage(Utilizer.rangeArray[0], temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
                g.drawImage(Utilizer.rangeArray[Utilizer.MAP[temp.getRowPos()][temp.getColPos()] - 1], temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
            }
        }
    }

    @Override
    public void drawPathOnHero(Graphics g, Hero hero,Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drawSkillOnHero(Graphics g, Hero hero, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //calculate skill range
    public ArrayList<Cell> calculateRange(int x, int y, double remaining){
        if(remaining == 0)
            return rangeCell;

        //if(Utilizer.MOVEMAP[x][y] == 0)
            rangeCell.add(new Cell(x,y));

        if(y+1 < 40)
            calculateRange(x, y + 1, remaining - 1); //up
        if(x-1 > 0)
            calculateRange(x - 1, y, remaining - 1); //left
        if(y-1 > 0)
            calculateRange(x, y - 1, remaining - 1); //down
        if(x+1 < 40)
            calculateRange(x + 1, y, remaining - 1); //right
        return rangeCell;
    }

    //draw skill range
    public void drawRange(Graphics g,int scrollX,int scrollY){
        ArrayList<Cell> cells=getRangeCell();
        for(Cell cell:cells){
            g.clearRect(cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
            g.drawImage(Utilizer.selectArray[0], cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, panel);
            g.drawImage(Utilizer.selectArray[Utilizer.MAP[cell.getRowPos()][cell.getColPos()] - 1], cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, panel);
        }
    }
    public ArrayList<Cell> getRangeCell(){
        return rangeCell;
    }
    public void clearRangeCell(){
        rangeCell.clear();
    }
}
