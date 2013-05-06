package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 5/6/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuitRequest implements Serializable {
    private int gameIndex,heroSlot;

    public QuitRequest(int gameIndex, int heroSlot) {
        this.gameIndex = gameIndex;
        this.heroSlot = heroSlot;
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
