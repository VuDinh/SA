package model.HeroSystem;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/13/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeroLibrary {
    private ArrayList<Hero> heroes;
    public HeroLibrary(){
        heroes=new ArrayList<Hero>();
        HeroFactory hF=new HeroFactory();
        for(int i=1;i<=7;i++){
            heroes.add(hF.createHero(i));
        }
    }
    public ArrayList<Hero> getHeroList(){
        return heroes;
    }
    public Hero getHero(int index){
        return heroes.get(index);
    }
    public int getNumberOfHeroes(){
        return heroes.size();
    }
}
