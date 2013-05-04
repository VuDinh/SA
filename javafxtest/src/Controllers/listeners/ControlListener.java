package Controllers.listeners;

import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;
import model.Skills.AOESkill;
import model.Skills.SkillStatus;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/9/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControlListener implements MouseListener {
    GameMap panel;
    Point pressed;
    public ControlListener(GameMap panel)
    {
        this.panel=panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        Point curPos=e.getPoint();
        double x = curPos.getX();
        double y = curPos.getY();
        Hero mainHero=panel.getFacade().getMainHero();
        if (mainHero.getIsChosen() && (mainHero.getStatus().equals(HeroStatus.standing) || mainHero.getStatus().equals(HeroStatus.attacking))){
        if((405<x && x<485) && (60<y && y<140)){
            mainHero.setStatus(HeroStatus.attacking);
            mainHero.getSkill(0).setStatus(SkillStatus.before);
            mainHero.setCurrentSkill(0);
            if(mainHero.getCurrentSkill() instanceof AOESkill){
                ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                        mainHero.getCol(), mainHero.getCurrentSkill().getRange()+1);
                ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
            }
        }

        else if((515<x && x<595) && (60<y && y<140)){
            mainHero.setStatus(HeroStatus.attacking);
            mainHero.getSkill(1).setStatus(SkillStatus.before);
            mainHero.setCurrentSkill(1);
            if(mainHero.getCurrentSkill() instanceof AOESkill){
                ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                        mainHero.getCol(), mainHero.getCurrentSkill().getRange()+1);
                ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
            }
        }

        else if((630<x && x<710) && (60<y && y<140)){
            mainHero.setStatus(HeroStatus.attacking);
            mainHero.getSkill(2).setStatus(SkillStatus.before);
            mainHero.setCurrentSkill(2);
            if(mainHero.getCurrentSkill() instanceof AOESkill){
                ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                        mainHero.getCol(), mainHero.getCurrentSkill().getRange()+1);
                ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
            }
        }
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
}
