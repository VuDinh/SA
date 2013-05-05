package model.MonsterSystem;

import Controllers.Server.GameManager.Team;
import Utilities.Utilizer;
import View.Ingame.Cell;
import model.*;
import model.Character;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 5/5/13
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tower extends Character implements Cloneable,Serializable {
    private boolean main, broken;
    private ArrayList<Cell> sight = new ArrayList<Cell>();
    private ArrayList<Cell> self = new ArrayList<Cell>();
    private Team team;

    public Tower(int HP, int maxHP, int AP, int maxAP, int Attk, int row, int col,int imageIndex,String name,
                    String description,Cell start, Cell end, Team team, boolean main, boolean broken){
        super(HP, maxHP, AP, maxAP, Attk, row, col, imageIndex,name,description);
        this.main = main;
        this.broken = broken;
        this.team = team;
        for (int i = start.getRowPos(); i <= end.getRowPos(); i++) {
            for (int j = start.getColPos(); j <= end.getColPos(); j++)  {
                self.add(new Cell(i, j));
            }
        }
    }

    public ArrayList<Cell> getSelf() {
        return self;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public void setSelf(ArrayList<Cell> self) {
        this.self = self;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
    public Tower clone() throws CloneNotSupportedException{
        return (Tower)super.clone();
    }

    public void setSight(ArrayList<Cell> sight) {
        this.sight = sight;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public ArrayList<Cell> calculateSight(int x, int y, double remaining) {
        if(remaining == 0)
            return sight;

        if(Utilizer.SIGHTMAP[x][y] == 0)
            sight.add(new Cell(x,y));
        else return sight;

        if(y+1 < 40)
            calculateSight(x, y + 1, remaining - 1); //up
        if(x-1 >= 0)
            calculateSight(x - 1, y, remaining - 1); //left
        if(y-1 >= 0)
            calculateSight(x, y - 1, remaining - 1); //down
        if(x+1 < 40)
            calculateSight(x + 1, y, remaining - 1); //right
        return sight;
    }
    public void clearSight(){
        sight.clear();
    }
    public ArrayList<Cell> getSight(){
        //sight.clear();
        calculateSight(row,col,5);
        return sight;
    }

    public boolean isHit(ArrayList<Cell> path){
        ArrayList<Cell> temp = new ArrayList<Cell>();
        ArrayList<Cell> dump = new ArrayList<Cell>();
        temp.addAll(path);
        dump.addAll(self);
        dump.retainAll(temp);
        if(self.size()!=dump.size()){
            return true;
        }
        return false;
    }
//    public static void main (String args[] ){
//        ArrayList<Cell>c= new ArrayList<Cell>(){{
//            add(new Cell(0,1));
//            add(new Cell(1,1));
//            add(new Cell(2,1));
//        }};
//        ArrayList<Cell>d= new ArrayList<Cell>(){{
//            add(new Cell(1,0));
//            add(new Cell(1,3));
//            add(new Cell(1,2));
//            add(new Cell(0,1));
//            add(new Cell(1,1));
//            //add(new Cell(2,1));
//        }};
//        System.out.println(c);
//        System.out.println(c.retainAll(d));
//        System.out.println(c);
//    }

}
