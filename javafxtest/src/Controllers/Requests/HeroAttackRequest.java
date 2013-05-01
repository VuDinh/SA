package Controllers.Requests;

import View.Ingame.Cell;
import model.HeroSystem.Hero;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/28/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroAttackRequest implements Serializable {
    private int gameIndex,slotIndex;
    private Hero hero;
    private Cell selectedCell;
    private ArrayList<Cell> path;
    public HeroAttackRequest(int gameIndex, int slotIndex, Hero hero) {
        this.gameIndex = gameIndex;
        this.slotIndex = slotIndex;
        this.hero = hero;
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

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }
    public void setPath(ArrayList<Cell> path){
        this.path=new ArrayList<Cell>();
        for(Cell cell:path){
            try {
                this.path.add(cell.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public ArrayList<Cell> getPath(){
        return path;
    }
}
