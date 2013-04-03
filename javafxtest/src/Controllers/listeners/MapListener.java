package Controllers.listeners;

import View.Ingame.Cell;
import View.Ingame.GameMap;
import Utilities.Utilizer;
import model.HeroSystem.HeroAttackThread;
import model.HeroSystem.HeroMoveThread;
import model.HeroSystem.HeroStatus;
import model.Skills.AOESkill;
import model.Skills.SkillStatus;
import model.Skills.SkillThread;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/5/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapListener implements MouseListener,MouseMotionListener {
    GameMap panel;
    Point pressed;
    public MapListener(GameMap panel)
    {
        this.panel=panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {


        //To change body of implemented methods use File | Settings | File Templates.
        GameMap temp= panel;
        temp.setStatus("selected");
        Cell selectCell= new Cell();
        Point curPos=e.getPoint();
        int x=(curPos.x + panel.getScrollX())/ Utilizer.TILE_SIZE;
        selectCell.setColPos(x);
        x=x*Utilizer.TILE_SIZE;
        selectCell.setX(x);
        int y=(curPos.y + panel.getScrollY())/Utilizer.TILE_SIZE;
        selectCell.setRowPos(y);
        y=y*Utilizer.TILE_SIZE;
        selectCell.setY(y);
        temp.setSelectedCell(selectCell);
        panel.requestFocus();
        if(e.isMetaDown()){
            panel.getHero().setIsChosen(false);
        }
        //To set Hero
        if(selectCell.getRowPos()==panel.getHero().getRow() && selectCell.getColPos()==panel.getHero().getCol()){
            panel.getHero().setIsChosen(true);
            panel.getHero().setStatus(HeroStatus.standing);
            panel.getHero().resetPath();

            panel.getHero().calculateRange(panel.getHero().getRow(), panel.getHero().getCol(), ((int)panel.getHero().getAP() / 2) + 1);
            if(( panel.getHero().getCurrentSkill())!=null){
                if( panel.getHero().getCurrentSkill() instanceof AOESkill) ((AOESkill) panel.getHero().getCurrentSkill()).clearRangeCell();  }
        }
        else if(panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.standing) && Utilizer.inRange(selectCell,
                panel.getHero().calculateRange(panel.getHero().getRow(), panel.getHero().getCol(), ((int)panel.getHero().getAP() / 2) + 1)))
        {

            panel.getHero().clearRange();
            panel.getHero().setStatus(HeroStatus.moving);
            panel.getHero().setShortestPathSelect(panel.getHero().getShortestpathHover());
            HeroMoveThread t=new HeroMoveThread(panel.getHero(), panel);
            t.start();

        }
        else if(panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.attacking)
                && panel.getHero().getCurrentSkill().getStatus().equals(SkillStatus.before)
                && panel.getHero().getCurrentSkill().getPath().contains(selectCell)
                && (panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP())>=0
                && Utilizer.inRange(selectCell,panel.getHero().getCurrentSkill().getRangeCell())){
            panel.getHero().getCurrentSkill().setStatus(SkillStatus.after);
            Utilizer.playWAV(panel.getHero().getCurrentSkill().getSE(),0);
            SkillThread t=new SkillThread(panel,panel.getHero().getCurrentSkill());
            t.start();
            HeroAttackThread t2 = new HeroAttackThread(panel.getHero(),panel);
            t2.start();
            panel.getHero().clearRange();
            panel.getHero().setAP(panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP());
            if(Utilizer.inRange(new Cell(panel.getMonster().getCol(),panel.getMonster().getRow()),
                    panel.getHero().getCurrentSkill().getPath())){
                panel.getMonster().setHP(panel.getMonster().getHP()-panel.getHero().getCurrentSkill().getDamage());
            }
        }
        else if(panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.attacking)
                && panel.getHero().getCurrentSkill().getStatus().equals(SkillStatus.before)
                && panel.getHero().getCurrentSkill().getPath().contains(selectCell)
                && (panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP())>=0
                && ! (panel.getHero().getCurrentSkill() instanceof AOESkill)){
            panel.getHero().getCurrentSkill().setStatus(SkillStatus.after);
            Utilizer.playWAV(panel.getHero().getCurrentSkill().getSE(),0);
            SkillThread t=new SkillThread(panel,panel.getHero().getCurrentSkill());
            t.start();
            HeroAttackThread t2 = new HeroAttackThread(panel.getHero(),panel);
            t2.start();
            panel.getHero().clearRange();
            panel.getHero().setAP(panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP());
        }
        panel.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        pressed=e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        Point dragged= e.getPoint();
        int x=dragged.x-pressed.x;
        int y=dragged.y-pressed.y;
        if( y>0 ){
           panel.decreaseScrollY();
        }
        if(y <0){
           panel.increaseScrollY();
        }
        if(x>0){
            panel.decreaseScrollX();
        }
        if(x<0){
            panel.increaseScrollX();
        }
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        GameMap temp=(GameMap) panel;
        Cell rangeCell= new Cell();
        Point curPos=e.getPoint();
        int x=(curPos.x + panel.getScrollX()) / Utilizer.TILE_SIZE;
        rangeCell.setColPos(x);
        x=x*Utilizer.TILE_SIZE;
        rangeCell.setX(x);
        int y=(curPos.y+panel.getScrollY())/Utilizer.TILE_SIZE;
        rangeCell.setRowPos(y);
        y=y*Utilizer.TILE_SIZE;
        rangeCell.setY(y);
        temp.setRangedCell(rangeCell);

        Cell selectCell = new Cell();
        selectCell.setColPos(x);
        selectCell.setX(x);
        selectCell.setRowPos(y);
        selectCell.setY(y);

        //set hero movement
        if(panel.getHero().getIsChosen()
                //&& inRange(selectCell,panel.getHero().calculateRange(panel.getHero().getRow(),panel.getHero().getCol(),(panel.getHero().getAP()/2) +1 ))
                )
        {
            panel.getHero().calculateShortestPath(rangeCell);
        }
        panel.repaint();
    }






}
