package Controllers.Requests;

import View.Ingame.Cell;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author Names: Dang Hoang Long - Vu Dinh
 * Author IDs: s3324816 - s3324817
 * Date: 5/5/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroRespawnRequest implements Serializable {
    int gameIndex,heroSlot;
    Cell respawnPos;
    public HeroRespawnRequest(int gameIndex,int heroSlot){
        this.gameIndex = gameIndex;
        this.heroSlot=heroSlot;

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
    public void setRespawnPos(Cell respawnPos){
        this.respawnPos=respawnPos;
    }
    public Cell getRespawnPos(){
        return respawnPos;
    }

}
