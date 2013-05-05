package Controllers.listeners;

import Utilities.Utilizer;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;
import model.Skills.AOESkill;
import model.Skills.SkillStatus;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/9/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControlListener implements MouseListener,MouseMotionListener {
    GameMap panel;
    Point pressed;

    public ControlListener(GameMap panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        Point curPos = e.getPoint();
        double x = curPos.getX();
        double y = curPos.getY();
        Hero mainHero = panel.getFacade().getMainHero();
        if (10 <= x && x<=140 && 10<=y && y<=140) {
            double relativeX=x-10;
            double relativeY=y-10;
            relativeX=relativeX/3.25 * Utilizer.TILE_SIZE +1;
            relativeY=relativeY/3.25 * Utilizer.TILE_SIZE +1;
            int scrollX=Math.max(0,(int)relativeX-panel.getWidth()/2);
            int scrollY=Math.max(0,(int)relativeY-panel.getHeight()/2);
            int actualScrollX=Math.min(scrollX,Utilizer.MAP_COLS*Utilizer.TILE_SIZE - panel.getWidth());
            int actualScrollY=Math.min(scrollY,Utilizer.MAP_ROWS*Utilizer.TILE_SIZE - panel.getHeight());
            panel.setScroll(actualScrollX, actualScrollY);

        }
        if (panel.getFacade().getIsLocked()) {
            panel.repaint();
            return;
        }
        if (mainHero.getIsChosen() && (mainHero.getStatus().equals(HeroStatus.standing) || mainHero.getStatus().equals(HeroStatus.attacking))) {
            if ((405 < x && x < 485) && (60 < y && y < 140)) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(0).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(0);
                if (mainHero.getCurrentSkill() instanceof AOESkill) {
                    ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                            mainHero.getCol(), mainHero.getCurrentSkill().getRange() + 1);
                    ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(), panel.getScrollX(), panel.getScrollY());
                }
            } else if ((515 < x && x < 595) && (60 < y && y < 140)) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(1).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(1);
                if (mainHero.getCurrentSkill() instanceof AOESkill) {
                    ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                            mainHero.getCol(), mainHero.getCurrentSkill().getRange() + 1);
                    ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(), panel.getScrollX(), panel.getScrollY());
                }
            } else if ((630 < x && x < 710) && (60 < y && y < 140)) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(2).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(2);
                if (mainHero.getCurrentSkill() instanceof AOESkill) {
                    ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                            mainHero.getCol(), mainHero.getCurrentSkill().getRange() + 1);
                    ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(), panel.getScrollX(), panel.getScrollY());
                }
            }
            //scroll by mini map
        }
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPos = e.getPoint();
        double x = curPos.getX();
        double y = curPos.getY();
        if (10 <= x && x<=140 && 10<=y && y<=140) {
            double relativeX=x-10;
            double relativeY=y-10;
            relativeX=relativeX/3.25 * Utilizer.TILE_SIZE +1;
            relativeY=relativeY/3.25 * Utilizer.TILE_SIZE +1;
            int scrollX=Math.max(0,(int)relativeX-panel.getWidth()/2);
            int scrollY=Math.max(0,(int)relativeY-panel.getHeight()/2);
            int actualScrollX=Math.min(scrollX,Utilizer.MAP_COLS*Utilizer.TILE_SIZE - panel.getWidth());
            int actualScrollY=Math.min(scrollY,Utilizer.MAP_ROWS*Utilizer.TILE_SIZE - panel.getHeight());
            panel.setScroll(actualScrollX, actualScrollY);

        }
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
