package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/2/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TurnControlRequest implements Serializable {
    private int turnIndex,gameIndex, heroSlot;
    public TurnControlRequest(int turnIndex,int gameIndex){
        this.turnIndex=turnIndex;
        this.gameIndex = gameIndex;
    }

    public int getTurnIndex() {
        return turnIndex;
    }

    public void setTurnIndex(int turnIndex) {
        this.turnIndex = turnIndex;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public int getHeroSlot() {
        return heroSlot;
    }

    public void setHeroSlot(int heroSlot) {
        this.heroSlot = heroSlot;
    }
}
