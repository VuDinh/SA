package model.Facade;

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
    HeroLibrary hL;
    int heroSlot,gameIndex;

    public Facade(){
        hL =new HeroLibrary();
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
}
