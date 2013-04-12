package Controllers.listeners;

import View.Ingame.GameMap;
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

        if (panel.getHero().getIsChosen() && (panel.getHero().getStatus().equals(HeroStatus.standing) || panel.getHero().getStatus().equals(HeroStatus.attacking))){
        if((405<x && x<485) && (60<y && y<140)){
            panel.getHero().setStatus(HeroStatus.attacking);
            panel.getHero().getSkill(0).setStatus(SkillStatus.before);
            panel.getHero().setCurrentSkill(0);
            if(panel.getHero().getCurrentSkill() instanceof AOESkill){
                ((AOESkill) panel.getHero().getCurrentSkill()).calculateRange(panel.getHero().getRow(),
                        panel.getHero().getCol(), panel.getHero().getCurrentSkill().getRange()+1);
                ((AOESkill) panel.getHero().getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
            }
        }

        else if((515<x && x<595) && (60<y && y<140)){
            panel.getHero().setStatus(HeroStatus.attacking);
            panel.getHero().getSkill(1).setStatus(SkillStatus.before);
            panel.getHero().setCurrentSkill(1);
            if(panel.getHero().getCurrentSkill() instanceof AOESkill){
                ((AOESkill) panel.getHero().getCurrentSkill()).calculateRange(panel.getHero().getRow(),
                        panel.getHero().getCol(), panel.getHero().getCurrentSkill().getRange()+1);
                ((AOESkill) panel.getHero().getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
            }
        }

        else if((630<x && x<710) && (60<y && y<140)){
            panel.getHero().setStatus(HeroStatus.attacking);
            panel.getHero().getSkill(2).setStatus(SkillStatus.before);
            panel.getHero().setCurrentSkill(2);
            if(panel.getHero().getCurrentSkill() instanceof AOESkill){
                ((AOESkill) panel.getHero().getCurrentSkill()).calculateRange(panel.getHero().getRow(),
                        panel.getHero().getCol(), panel.getHero().getCurrentSkill().getRange()+1);
                ((AOESkill) panel.getHero().getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
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
