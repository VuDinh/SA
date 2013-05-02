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
public class Player implements Serializable,Cloneable {
    private transient Communicator com;
    private boolean isPicked;
    private int heroIndex;
    private int slotIndex;
    private Team team;
    private Cell cell;
    private Hero hero;
    private String playerName;

    public Player(Communicator com,int slotIndex,Team team){
        this.com=com;
        isPicked = false;
        this.slotIndex=slotIndex;
        this.team=team;
        playerName = new String(com.getAccount().getUsername());
        System.out.println(playerName);
    }
    public Player(Player player){
        this.slotIndex=player.slotIndex;
        this.team=player.team;
        this.isPicked=player.isPicked();
        this.heroIndex=player.heroIndex;
        this.hero = new Hero(player.hero);
        this.playerName = player.playerName;
        System.out.println(this.hero.getY());
    }
    public Player(int slotIndex,Team team){
        isPicked = false;
        this.slotIndex=slotIndex;
        this.team=team;
    }
    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    public Team getTeam(){
        return team;
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
    public Cell getCell(){
        return cell;
    }
    public void setHero(Hero hero){
        this.hero=hero;
        System.out.println(hero);
        this.hero.setRow(cell.getRowPos());
        this.hero.setCol(cell.getColPos());
        this.hero.setX(cell.getX());
        this.hero.setY(cell.getY());
    }
    public Hero getHero(){
        return hero;
    }
    public Player clone() throws CloneNotSupportedException {
        Player player=new Player(this.slotIndex,this.team);
        player.setHeroIndex(heroIndex);
        player.hero = new Hero(this.hero);
        player.playerName=this.playerName;
        return player;
    }
}
