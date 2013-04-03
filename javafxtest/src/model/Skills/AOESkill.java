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

    public AOESkill(int range, String name, boolean stun, boolean slow, int imageIndex, int ID, int damage,
                    String SE, int AP) {
        super(range,name,stun,slow,imageIndex,ID,damage,SE,AP);

    }

    public void calculatePath(Cell to) {
        path.clear();
        int startRow, endRow, startCol, endCol;

        for (int i = to.getRowPos() - 1; i <= to.getRowPos() + 1; i++) {
            for (int j = to.getColPos() - 1; j <= to.getColPos() + 1; j++)
                if (Utilizer.MOVEMAP[i][j] == 0) {
                    path.add(new Cell(i, j));
                }
        }
    }

    @Override
    public void drawSkill(Graphics g,Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.after) {
            for (Iterator i = path.iterator(); i.hasNext(); ) {
                Cell temp = (Cell) i.next();

                g.drawImage(getCurrentSprite(),temp.getColPos() * Utilizer.TILE_SIZE - scrollX, temp.getRowPos() * Utilizer.TILE_SIZE - scrollY, panel);
            }
        }
    }

    @Override
    public void drawPath(Graphics g, Cell to, int scrollX, int scrollY, GameMap panel) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (status == SkillStatus.before) {
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

    public ArrayList<Cell> calculateRange(int x, int y, double remaining){
        if(remaining == 0)
            return rangeCell;

        if(Utilizer.MOVEMAP[x][y] == 0)
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
