package Controllers.Requests;

import model.HeroSystem.Hero;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/27/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroMoveRequest implements Serializable {
    private int gameIndex,slotIndex;
    private Hero hero;
    public HeroMoveRequest(int gameIndex,int slotIndex,Hero hero){
        this.gameIndex = gameIndex;
        this.slotIndex = slotIndex;
        this.hero=hero;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
