package model.HeroSystem;
import View.Ingame.Cell;
import Utilities.Utilizer;
import model.Character;
import model.ItemSystem.Item;
import model.Skills.AOESkill;
import model.Skills.CleaveSkill;
import model.Skills.NormalSkill;
import model.Skills.Skill;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hero extends Character implements Serializable,Cloneable {
    ArrayList<Skill> skills;
    int skillCount;
    ArrayList<Item> inventory;
    Cell[][] map;
    ArrayList<Cell> shortestpathHover,shortestPathSelect;
    int currentSkill;
    HeroStatus status;
    ArrayList<Cell> range;
    protected Hero(int HP, int maxHP, int AP, int maxAP, int Attk, int row, int col, int imageIndex ,String name, String description) {
        super(HP, maxHP, AP, maxAP, Attk, row, col, imageIndex ,name, description);
        skills=new ArrayList<Skill>();
        inventory=new ArrayList<Item>();
        skillCount = 0;
        map=new Cell[Utilizer.MAP_ROWS][Utilizer.MAP_COLS];
        shortestpathHover=new ArrayList<Cell>();
        status=HeroStatus.standing;
        currentSkill = -1;
        range=new ArrayList<Cell>();
    }
    public Hero(Hero hero){
        super(hero);
        this.skillCount = 0;
        inventory=new ArrayList<Item>();
        this.skills=new ArrayList<Skill>();
        for(Skill skill:hero.skills){
            if(skill instanceof NormalSkill)
            this.skills.add(new NormalSkill(skill));
            if(skill instanceof AOESkill)
                this.skills.add(new AOESkill(skill));
            if(skill instanceof CleaveSkill)
                this.skills.add(new CleaveSkill(skill));
        }
        map=new Cell[Utilizer.MAP_ROWS][Utilizer.MAP_COLS];
        shortestpathHover=new ArrayList<Cell>();
        status=HeroStatus.standing;
        currentSkill = -1;
        range=new ArrayList<Cell>();
    }
    protected Hero(){
        super();
        skills=new ArrayList<Skill>();
        inventory=new ArrayList<Item>();
        skillCount = 0;
        map=new Cell[Utilizer.MAP_ROWS][Utilizer.MAP_COLS];
        shortestpathHover=new ArrayList<Cell>();
        status=HeroStatus.standing;
        currentSkill = -1;
    }

    public ArrayList<Cell> getShortestPathSelect() {
        return shortestPathSelect;
    }

    public void setShortestPathSelect(ArrayList<Cell> shortestPathSelect) {
        this.shortestPathSelect = shortestPathSelect;
    }

    public ArrayList<Cell> getShortestpathHover() {
        return shortestpathHover;
    }

    public void setShortestpathHover(ArrayList<Cell> shortestpathHover) {
        this.shortestpathHover = shortestpathHover;
    }

    public HeroStatus getStatus() {
        return status;
    }

    public void setStatus(HeroStatus status) {
        this.status = status;
    }

    //draw hero
    @Override
    public void draw(Graphics g,int scrollX,int scrollY) {
        //To change body of implemented methods use File | Settings | File Templates.
        ArrayList<Cell> path=getShortestpathHover();
        if(isChosen && !path.isEmpty() && status==HeroStatus.standing){
            for(Iterator i=getShortestpathHover().iterator();i.hasNext();){
                Cell temp=(Cell)i.next();
                g.clearRect(temp.getColPos()*Utilizer.TILE_SIZE - scrollX, temp.getRowPos()*Utilizer.TILE_SIZE - scrollY,Utilizer.TILE_SIZE,Utilizer.TILE_SIZE);
                g.drawImage(Utilizer.selectArray[0],temp.getColPos()*Utilizer.TILE_SIZE - scrollX,temp.getRowPos()*Utilizer.TILE_SIZE - scrollY,getPanel());
                g.drawImage(Utilizer.selectArray[Utilizer.MAP[temp.getRowPos()][temp.getColPos()] - 1],temp.getColPos()*Utilizer.TILE_SIZE - scrollX,temp.getRowPos()*Utilizer.TILE_SIZE - scrollY,getPanel());
            }
        }
        g.drawImage(getCurrentSprite(),getX() -scrollX,getY() -scrollY,getPanel());
    }

    //set next sprite
    @Override
    public void nextSprite() {
        //To change body of implemented methods use File | Settings | File Templates.
        if(currentSprite % 4 == 3) currentSprite-=3;
        else currentSprite++;
    }

    //move character to the specified location
    @Override
    public void moveCharacter(int col,int row) {
        //move right
        if(col- this.col > 0 && row==this.row)
        {
            dirX=12;
            dirY=0;
            distanceX= (col - this.col)*Utilizer.TILE_SIZE;
            distanceY = 0;
            currentSprite=8;
        }
        //move left
        if(col- this.col < 0 && row==this.row)
        {
            dirX=-12;
            dirY=0;
            distanceX= (this.col-col)*Utilizer.TILE_SIZE;
            distanceY = 0;
            currentSprite=4;
        }
        //move up
        if(col == this.col && row<this.row)
        {
            dirX=0;
            dirY=-12;
            distanceX= 0;
            distanceY = (this.row - row)*Utilizer.TILE_SIZE;
            currentSprite=12;
        }
        //move down
        if(col == this.col && row>this.row)
        {
            dirX=0;
            dirY=12;
            distanceX= 0;
            distanceY = (row - this.row)*Utilizer.TILE_SIZE;
            currentSprite=0;
        }
        this.col=col;
        this.row=row;
        resetPath();
    }
    //get All skills
    public ArrayList<Skill> getAllSkills(){
        return skills;
    }
    //move 1/4 cell and decrease ap
    @Override
    public void move() {
        //To change body of implemented methods use File | Settings | File Templates.
        x+=dirX;
        y+=dirY;
        distanceX-=Math.abs(dirX);
        distanceY-=Math.abs(dirY);
        setAP(getAP()-0.5);
    }

    //reset the shortest path each time the hero moves
    @Override
    public void resetPath() {
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map[0].length;j++)
                map[i][j]=new Cell(i,j);

        map[row][col].setMinPath(0);
        PriorityQueue<Cell> queue=new PriorityQueue<Cell>();
        queue.add(map[row][col]);
        while(!queue.isEmpty()){
            Cell temp= queue.poll();
            int precost=temp.getMinPath()+1;
            if(temp.getRowPos()>0){
                if(Utilizer.MOVEMAP[temp.getRowPos()-1][temp.getColPos()] == 0 && map[temp.getRowPos()-1][temp.getColPos()].getMinPath() > precost ){
                    //queue.remove(map[temp.getRowPos()-1][temp.getColPos()]);
                    map[temp.getRowPos()-1][temp.getColPos()].setMinPath(precost);
                    map[temp.getRowPos()-1][temp.getColPos()].setPrevious(temp);
                    queue.add(map[temp.getRowPos()-1][temp.getColPos()]);
                }
            }

            if(temp.getRowPos()<map.length-1){
                if(Utilizer.MOVEMAP[temp.getRowPos()+1][temp.getColPos()] == 0 && map[temp.getRowPos()+1][temp.getColPos()].getMinPath() > precost ){
                    //queue.remove(map[temp.getRowPos()+1][temp.getColPos()]);
                    map[temp.getRowPos()+1][temp.getColPos()].setMinPath(precost);
                    map[temp.getRowPos()+1][temp.getColPos()].setPrevious(temp);
                    queue.add(map[temp.getRowPos()+1][temp.getColPos()]);
                }
            }

            if(temp.getColPos()>0){
                if(Utilizer.MOVEMAP[temp.getRowPos()][temp.getColPos()-1] == 0 && map[temp.getRowPos()][temp.getColPos()-1].getMinPath() > precost ){
                    //queue.remove(map[temp.getRowPos()][temp.getColPos()-1]);
                    map[temp.getRowPos()][temp.getColPos()-1].setMinPath(precost);
                    map[temp.getRowPos()][temp.getColPos()-1].setPrevious(temp);
                    queue.add(map[temp.getRowPos()][temp.getColPos()-1]);
                }
            }

            if(temp.getColPos()<map[0].length-1){
                if(Utilizer.MOVEMAP[temp.getRowPos()][temp.getColPos()+1] == 0 && map[temp.getRowPos()][temp.getColPos()+1].getMinPath() > precost ){
                    //queue.remove(map[temp.getRowPos()][temp.getColPos()+1]);
                    map[temp.getRowPos()][temp.getColPos()+1].setMinPath(precost);
                    map[temp.getRowPos()][temp.getColPos()+1].setPrevious(temp);
                    queue.add(map[temp.getRowPos()][temp.getColPos()+1]);
                }
            }
        }
    }

    //calculate shortest path
    public ArrayList<Cell> calculateShortestPath(Cell to){
        if(Utilizer.MOVEMAP[to.getRowPos()][to.getColPos()]==0){
            ArrayList<Cell> path=new ArrayList<Cell>();
            for(Cell i=map[to.getRowPos()][to.getColPos()];i!=null;i=i.getPrevious()) {
                path.add(i);
            }
            Collections.reverse(path);
            shortestpathHover =path;
            return path;
        }
        return null;
    }

    //add skills
    public void addSkill(Skill skill){
        if(skillCount<3){
            skill.setImgHeroIndex(imageIndex);
            skills.add(skill);
            skillCount++;
        }
    }
    public Skill getSkill(int index){
        return skills.get(index);
    }

    public int getCurrentSkillIndex() {
        return currentSkill;
    }
    public Skill getCurrentSkill(){
        if(currentSkill<0)return null;
        return skills.get(currentSkill);
    }
    public void setCurrentSkill(int currentSkill) {
        this.currentSkill = currentSkill;
    }

    public void addItem(Item item){
        inventory.add(item);
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public void removeItemByIndex(int index){
        inventory.remove(index);
    }

    //calculate movement Range
    public ArrayList<Cell> calculateRange(int x, int y, double remaining){
        if(remaining == 0)
            return range;

        if(Utilizer.MOVEMAP[x][y] == 0)
            range.add(new Cell(x,y));
        else    return range;

        if(y+1 < 40)
            calculateRange(x, y + 1, remaining - 1); //up
        if(x-1 > 0)
            calculateRange(x - 1, y, remaining - 1); //left
        if(y-1 > 0)
            calculateRange(x, y - 1, remaining - 1); //down
        if(x+1 < 40)
            calculateRange(x + 1, y, remaining - 1); //right
        return range;
    }

    //draw movement range
    public void drawRange(Graphics g,int scrollX,int scrollY){
        ArrayList<Cell> cells=getRange();
        for(Cell cell:cells){
            g.clearRect(cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
            g.drawImage(Utilizer.rangeArray[0], cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, panel);
            g.drawImage(Utilizer.rangeArray[Utilizer.MAP[cell.getRowPos()][cell.getColPos()] - 1], cell.getColPos()*Utilizer.TILE_SIZE - scrollX, cell.getRowPos()*Utilizer.TILE_SIZE - scrollY, panel);
        }
    }
    public ArrayList<Cell> getRange(){
        return range;
    }
    public void clearRange(){
        range.clear();
    }
    public Hero clone() throws CloneNotSupportedException{
        Hero hero= (Hero)super.clone();
        hero.skills=new ArrayList<Skill>();
        for(Skill skill:this.skills){
            hero.skills.add(skill.clone());
        }
        return hero;
    }
    public boolean isThere(int row, int col){
        if(row==this.row && col==this.col){
            return true;
        }
        return false;
    }
}
