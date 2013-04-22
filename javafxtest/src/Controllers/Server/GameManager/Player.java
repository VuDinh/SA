package Controllers.Server.GameManager;

import Controllers.Communicator;
import View.Ingame.Cell;
import model.HeroSystem.Hero;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/21/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Serializable {
    private transient Communicator com;
    private boolean isPicked;
    private int heroIndex;
    private int slotIndex;
    private Team team;
    private Cell cell;
    private Hero hero;
    public Player(){
        isPicked = false;
    }
    public Player(Communicator com,int slotIndex,Team team){
        this.com=com;
        isPicked = false;
        this.slotIndex=slotIndex;
        this.team=team;
    }

    public Communicator getCom() {
        return com;
    }

    public void setCom(Communicator com) {
        this.com = com;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }

    public int getHeroIndex() {
        return heroIndex;
    }

    public void setHeroIndex(int heroIndex) {
        this.heroIndex = heroIndex;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }
    public void setPosition(Cell cell){
        this.cell = cell;
    }
    public void setHero(Hero hero){
        this.hero=hero;
        hero.setRow(cell.getRowPos());
        hero.setCol(cell.getColPos());
    }
    public Hero getHero(){
        return hero;
    }
}
