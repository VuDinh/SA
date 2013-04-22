package model.Facade;

import Controllers.Server.GameManager.GameMatch;
import Controllers.Server.GameManager.Player;
import model.AccountSystem.Account;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroLibrary;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/13/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Facade {
    private HeroLibrary hL;
    private int heroSlot,gameIndex;
    private GameMatch match;
    private Account account;
    public Facade(){
        hL =new HeroLibrary();
        account=new Account();
    }
    public ArrayList<Hero> getHeroList(){
        return hL.getHeroList();
    }
    public Hero getLibraryHero(int index){
        return hL.getHero(index);
    }
    public int getNumberOfHeroes(){
        return hL.getNumberOfHeroes();
    }

    public int getHeroSlot() {
        return heroSlot;
    }

    public void setHeroSlot(int heroSlot) {
        this.heroSlot = heroSlot;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }
    public void setUsername(String username){
        account.setUsername(username);
    }
    public String getUsername(){
        return account.getUsername();
    }
    public Player getClientPlayer(){
        return match.getPlayer(heroSlot);
    }
    public Account getClientAccount(){
        return account;
    }

    public GameMatch getMatch() {
        return match;
    }

    public void setMatch(GameMatch match) {
        this.match = match;
    }
}
