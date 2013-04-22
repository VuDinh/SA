package Controllers.Requests;

import Controllers.Server.GameManager.GameMatch;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/14/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayingGameRequest implements Serializable {
    private int heroIndex,heroSlot,matchIndex;
    private GameMatch game;
    public PlayingGameRequest(){

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

    public int getMatchIndex() {
        return matchIndex;
    }

    public void setMatchIndex(int matchIndex) {
        this.matchIndex = matchIndex;
    }

    public GameMatch getGame() {
        return game;
    }

    public void setGame(GameMatch game) {
        this.game = game;
    }
}
