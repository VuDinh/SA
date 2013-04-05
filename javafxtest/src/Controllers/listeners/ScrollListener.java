package Controllers.listeners;

import View.Ingame.GameMap;
import model.HeroSystem.HeroStatus;
import model.Skills.AOESkill;
import model.Skills.SkillStatus;
import sun.security.jca.GetInstance;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 3/10/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScrollListener implements KeyListener {
    GameMap panel;
    int limitX;
    int limitY;

    public ScrollListener(GameMap panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.

    }
    //scroll the map by arrow keys and skill casting and recover ap
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_END) {
            panel.getHero().setAP(panel.getHero().getMaxAP());
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            panel.increaseScrollY();
            panel.getHero().setCurrentSprite(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            panel.decreaseScrollY();
            panel.getHero().setCurrentSprite(12);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            panel.decreaseScrollX();
            panel.getHero().setCurrentSprite(4);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            panel.increaseScrollX();
            panel.getHero().setCurrentSprite(8);
        }
        if (panel.getHero().getIsChosen() && panel.getHero().getStatus().equals(HeroStatus.standing)) {
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                panel.getHero().setStatus(HeroStatus.attacking);
                panel.getHero().getSkill(0).setStatus(SkillStatus.before);
                panel.getHero().setCurrentSkill(0);
                if(panel.getHero().getCurrentSkill() instanceof AOESkill){
                    ((AOESkill) panel.getHero().getCurrentSkill()).calculateRange(panel.getHero().getRow(),
                            panel.getHero().getCol(), panel.getHero().getCurrentSkill().getRange()+1);
                    ((AOESkill) panel.getHero().getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                panel.getHero().setStatus(HeroStatus.attacking);
                panel.getHero().getSkill(1).setStatus(SkillStatus.before);
                panel.getHero().setCurrentSkill(1);
                if(panel.getHero().getCurrentSkill() instanceof AOESkill){
                    ((AOESkill) panel.getHero().getCurrentSkill()).calculateRange(panel.getHero().getRow(),
                            panel.getHero().getCol(), panel.getHero().getCurrentSkill().getRange()+1);
                    ((AOESkill) panel.getHero().getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
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
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
