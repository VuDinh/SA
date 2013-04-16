package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/13/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroChoosingRequest implements Serializable {
    int gameIndex,heroSlot;
    public HeroChoosingRequest(){

    }
    public void setMatchIndex(int index){
        gameIndex = index;
    }
    public void setHeroSlotIndex(int index){
        heroSlot=index;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public int getHeroSlot() {
        return heroSlot;
    }
}
