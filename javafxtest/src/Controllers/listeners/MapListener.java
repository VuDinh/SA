package Controllers.listeners;

import Controllers.Communicator;
import Controllers.Requests.HeroAttackRequest;
import Controllers.Requests.HeroMoveRequest;
import View.Ingame.Cell;
import View.Ingame.ControlPanel;
import View.Ingame.GameMap;
import Utilities.Utilizer;
import javafx.animation.Animation;
import model.Animations.HeroAnimation;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroAttackThread;
import model.HeroSystem.HeroMoveThread;
import model.HeroSystem.HeroStatus;
import model.Skills.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
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
    ControlPanel controlPanel;
    boolean lock;
    Communicator com;
    Hero mainHero;
    public MapListener(Communicator com,GameMap panel,ControlPanel controlPanel)
    {
        this.com=com;
        this.panel=panel;
        this.controlPanel=controlPanel;
        lock=false;
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
            panel.getFacade().getMainHero().setIsChosen(false);

        }
        Hero clickedHero=panel.getFacade().getHeroByCord(selectCell.getRowPos(),selectCell.getColPos());
        mainHero = panel.getFacade().getMainHero();
        if(clickedHero!=null && !mainHero.getStatus().equals(HeroStatus.attacking)){
            if(mainHero.getStatus().equals(HeroStatus.standing)) controlPanel.setHero(clickedHero);
            panel.getFacade().setCurrentHero(clickedHero);
            if(clickedHero.equals(mainHero)){
                mainHero.setIsChosen(true);
                mainHero.resetPath();
                mainHero.calculateRange(mainHero.getRow(), mainHero.getCol(), ((int) mainHero.getAP() / 2) + 1);
                if(( mainHero.getCurrentSkill())!=null){
                    if( mainHero.getCurrentSkill() instanceof AOESkill) ((AOESkill) mainHero.getCurrentSkill()).clearRangeCell();
                }
            }
            if(panel.getFacade().getIsLocked()) {
                panel.repaint();
                return;
            }
        }
        //To set Hero
        /*if(selectCell.getRowPos()==panel.getHero().getRow() && selectCell.getColPos()==panel.getHero().getCol()){
            panel.getHero().setIsChosen(true);
            panel.getHero().setStatus(HeroStatus.standing);
            panel.getHero().resetPath();
            panel.getHero().calculateRange(panel.getHero().getRow(), panel.getHero().getCol(), ((int) panel.getHero().getAP() / 2) + 1);
            if(( panel.getHero().getCurrentSkill())!=null){
                if( panel.getHero().getCurrentSkill() instanceof AOESkill) ((AOESkill) panel.getHero().getCurrentSkill()).clearRangeCell();
            }
        }*/
        else if(panel.getFacade().getIsLocked()) {
            panel.repaint();
            return;
        }
        else if(mainHero.getIsChosen() && mainHero.getStatus().equals(HeroStatus.standing) && Utilizer.inRange(selectCell,
                mainHero.calculateRange(mainHero.getRow(), mainHero.getCol(), ((int)mainHero.getAP() / 2) + 1)))
        {
            //send moving request
            int gameIndex=panel.getFacade().getGameIndex();
            int heroSlot=panel.getFacade().getHeroSlot();
            mainHero.setShortestPathSelect(mainHero.getShortestpathHover());

            HeroMoveRequest moveRequest= null;
            try {
                moveRequest = new HeroMoveRequest(gameIndex,heroSlot,mainHero.clone());
                moveRequest.setSelectedCell(selectCell.clone());
                com.write(moveRequest);
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            //HeroAnimation.move(mainHero,panel);
            if(( mainHero.getCurrentSkill())!=null){
                if( mainHero.getCurrentSkill() instanceof AOESkill) ((AOESkill) mainHero.getCurrentSkill()).clearRangeCell();  }
        }
        //cast AOE skill
        else if(mainHero.getIsChosen() && mainHero.getStatus().equals(HeroStatus.attacking)
                && mainHero.getCurrentSkill().getStatus().equals(SkillStatus.before)
                && mainHero.getCurrentSkill().getPath().contains(selectCell)
                && (mainHero.getAP()-mainHero.getCurrentSkill().getAP())>=0
                && mainHero.getCurrentSkill()!=null
                && Utilizer.inRange(selectCell, mainHero.getCurrentSkill().getRangeCell())){
            //send attack request
            int gameIndex=panel.getFacade().getGameIndex();
            int heroSlot=panel.getFacade().getHeroSlot();
            if(selectCell.getColPos() < mainHero.getCol())
                mainHero.setCurrentSprite(16);
            else mainHero.setCurrentSprite(20);

            HeroAttackRequest attackReq=null;
            if(mainHero.getCurrentSkill() instanceof NormalSkill)
                mainHero.getCurrentSkill().getDmgCell().clear();
            mainHero.getCurrentSkill().getDmgCell().add(selectCell);
            try {
                Hero cloneHero=mainHero.clone();

                //cloneHero.getCurrentSkill().clonePath(mainHero.getCurrentSkill().getPath());
                attackReq = new HeroAttackRequest(gameIndex,heroSlot,cloneHero);
                attackReq.setSelectedCell(selectCell.clone());
                attackReq.setPath(mainHero.getCurrentSkill().getPath());
                attackReq.setDmgCell(mainHero.getCurrentSkill().getDmgCell());
                System.out.println("sending skill path:"+ attackReq.getPath());
                com.write(attackReq);
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            /*if(mainHero.getCurrentSkill() instanceof NormalSkill) mainHero.getCurrentSkill().getDmgCell().clear();
            mainHero.getCurrentSkill().getDmgCell().add(selectCell);
            if(Utilizer.inRange(new Cell(panel.getMonster().getCol(),panel.getMonster().getRow()),
                    mainHero.getCurrentSkill().getDmgCell()) ){
                panel.getMonster().setHP(panel.getMonster().getHP()-panel.getHero().getCurrentSkill().getDamage(mainHero));
                panel.setDamage(mainHero.getCurrentSkill().getDamage(panel.getHero()));
            }else panel.setDamage(0);*/
        }

        /*//cast cleave skill
        else if(panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.attacking)
                && panel.getHero().getCurrentSkill().getStatus().equals(SkillStatus.before)
                && panel.getHero().getCurrentSkill().getPath().contains(selectCell)
                && (panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP())>=0
                && ! (panel.getHero().getCurrentSkill() instanceof AOESkill)
                && ! (panel.getHero().getCurrentSkill() instanceof NormalSkill)){
            HeroAnimation.attack(panel.getHero(),panel);
            if(Utilizer.inRange(new Cell(panel.getMonster().getCol(),panel.getMonster().getRow()),
                    panel.getHero().getCurrentSkill().getPath())){
                panel.getMonster().setHP(panel.getMonster().getHP()-panel.getHero().getCurrentSkill().getDamage());
            }
        }*/

        //cast normal skill
       /* else if(panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.attacking)
                && panel.getHero().getCurrentSkill().getStatus().equals(SkillStatus.before)
                && panel.getHero().getCurrentSkill().getPath().contains(selectCell)
                && (panel.getHero().getAP()-panel.getHero().getCurrentSkill().getAP())>=0
                && (panel.getHero().getCurrentSkill() instanceof NormalSkill)){
            HeroAnimation.attack(panel.getHero(),panel);
            Cell c = new Cell(panel.getMonster().getCol(),panel.getMonster().getRow());
            if(selectCell.equals(c)){
                panel.getMonster().setHP(panel.getMonster().getHP()-panel.getHero().getCurrentSkill().getDamage());
            }
        }*/
        //System.out.println(panel.getHero().getStatus());
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

    // schroll the map using mouse
    @Override
    public void mouseDragged(MouseEvent e) {
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

    //reset shortest path algorithm when the cursor moves
    @Override
    public void mouseMoved(MouseEvent e) {
        //set relative position of the cell that cursor points to base on actual map
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
        if(rangeCell.getRowPos()>=0
                && rangeCell.getRowPos()<=39
                && rangeCell.getColPos()>=0
                && rangeCell.getColPos()<=39)
        temp.setRangedCell(rangeCell);
        else return;

        //set hero movement
        if(panel.getFacade().getMainHero().getIsChosen()
                //&& inRange(selectCell,panel.getHero().calculateRange(panel.getHero().getRow(),panel.getHero().getCol(),(panel.getHero().getAP()/2) +1 ))
                )
        {
            mainHero.calculateShortestPath(rangeCell);
        }
        panel.repaint();
    }
}
