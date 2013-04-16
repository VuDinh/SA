package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/15/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroPickedRequest implements Serializable {
    private int heroIndex,heroSlot;
    private String playerName;
    public HeroPickedRequest(int heroIndex, int heroSlot) {
        this.heroIndex = heroIndex;
        this.heroSlot = heroSlot;
    }

    public int getHeroIndex() {
        return heroIndex;
    }

    public void setHeroIndex(int heroIndex) {
        this.heroIndex = heroIndex;
    }

    public int getHeroSlot() {
        return heroSlot;
    }

    public void setHeroSlot(int heroSlot) {
        this.heroSlot = heroSlot;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
