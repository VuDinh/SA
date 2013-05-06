package Controllers.listeners;

import Controllers.Communicator;
import Controllers.Requests.TurnControlRequest;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;
import model.Skills.AOESkill;
import model.Skills.CleaveSkill;
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
    Hero mainHero;
    Communicator com;
    public ScrollListener(Communicator com,GameMap panel) {
        this.panel = panel;
        this.com=com;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.

    }
    //scroll the map by arrow keys and skill casting and recover ap
    @Override
    public void keyPressed(KeyEvent e) {
        mainHero=panel.getFacade().getMainHero();
        if (e.getKeyCode() == KeyEvent.VK_END) {
            if(panel.getFacade().getIsLocked()==false)
            {
                mainHero.resetAP();
                TurnControlRequest request=new TurnControlRequest(panel.getFacade().getHeroSlot(),panel.getFacade().getGameIndex());
                request.setHeroSlot(panel.getFacade().getHeroSlot());
                panel.getFacade().setIsLocked(true);
                com.write(request);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            panel.increaseScrollY();
            mainHero.setCurrentSprite(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            panel.decreaseScrollY();
            mainHero.setCurrentSprite(12);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            panel.decreaseScrollX();
            mainHero.setCurrentSprite(4);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            panel.increaseScrollX();
            mainHero.setCurrentSprite(8);
        }
        if(e.getKeyCode() == KeyEvent.VK_F1){
            panel.setCenterScreenByCord(mainHero.getRow(),mainHero.getCol());
            mainHero.setIsChosen(true);
            mainHero.resetPath();
            mainHero.calculateRange(mainHero.getRow(), mainHero.getCol(), ((int) mainHero.getAP() / 2) + 1);
            if ((mainHero.getCurrentSkill()) != null) {
                if (mainHero.getCurrentSkill() instanceof AOESkill)
                    ((AOESkill) mainHero.getCurrentSkill()).clearRangeCell();
            }
        }
        if(panel.getFacade().getIsLocked()) {
            panel.repaint();
            return;
        }
        if (mainHero.getIsChosen() && (mainHero.getStatus().equals(HeroStatus.standing) || mainHero.getStatus().equals(HeroStatus.attacking))) {
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(0).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(0);

            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(1).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(1);
                if(mainHero.getCurrentSkill() instanceof AOESkill){
                    ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                            mainHero.getCol(), mainHero.getCurrentSkill().getRange()+1);
                    ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                mainHero.setStatus(HeroStatus.attacking);
                mainHero.getSkill(2).setStatus(SkillStatus.before);
                mainHero.setCurrentSkill(2);
                /*if(mainHero.getCurrentSkill() instanceof AOESkill){
                    ((AOESkill) mainHero.getCurrentSkill()).calculateRange(mainHero.getRow(),
                            mainHero.getCol(), mainHero.getCurrentSkill().getRange()+1);
                    ((AOESkill) mainHero.getCurrentSkill()).drawRange(panel.getGraphics(),panel.getScrollX(), panel.getScrollY());
                }*/
            }
        }
        //System.out.println(mainHero().getStatus());
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
