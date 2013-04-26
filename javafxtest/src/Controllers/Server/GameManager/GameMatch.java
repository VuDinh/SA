package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.HeroChoosingRequest;
import Controllers.Requests.HeroPickedRequest;
import Controllers.Requests.MatchMakingRequest;
import Controllers.Requests.PlayingGameRequest;
import Controllers.Server.AccountDao;
import View.Ingame.Cell;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;
import org.springframework.util.SerializationUtils;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/11/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameMatch implements Serializable, Cloneable {
    private ArrayList<Player> team1;
    private ArrayList<Player> team2;
    int counter;
    private boolean isFull;
    private transient AccountDao dao;
    private static final int MAX_PLAYER = 2;
    private int gameIndex;

    public GameMatch(int index) {
        counter = 0;
        team1 = new ArrayList<Player>();
        team2 = new ArrayList<Player>();
        isFull = false;
        this.gameIndex = index;
    }

    public GameMatch(GameMatch one) {
        this.team1 = new ArrayList<Player>();
        this.team2 = new ArrayList<Player>();
        for (Player player : one.team1) {
            this.team1.add(new Player(player));
        }
        for (Player player : one.team2) {
            this.team2.add(new Player(player));
        }
        this.counter = one.counter;
        this.isFull = one.isFull;
        this.gameIndex = one.gameIndex;

    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }

    public void addPlayer(Communicator com) {
        if (counter < MAX_PLAYER) {
            if (counter < MAX_PLAYER / 2) {
                team1.add(new Player(com, counter, Team.team1));
            } else {
                team2.add(new Player(com, counter, Team.team2));
            }
            counter++;
            announceFingdingMatchRequest();
        }
        if (counter == MAX_PLAYER) {
            isFull = true;
            announceHeroChoosingRequest();
            initiateCountDown();
        }

    }

    public void announceFingdingMatchRequest() {
        MatchMakingRequest m = new MatchMakingRequest(counter);
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(m);
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(m);
        }
    }

    public void announceHeroChoosingRequest() {
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            Player player = (Player) it.next();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(player.getSlotIndex());
            player.getCom().write(m);
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            Player player = (Player) it.next();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(player.getSlotIndex());
            player.getCom().write(m);
        }
    }

    public void chooseHero(Communicator com, int heroSlot, int heroIndex) {
        //announce to the others about the other hero picked
        HeroPickedRequest request = new HeroPickedRequest(heroIndex, heroSlot);
        Player temp = getPlayer(heroSlot);
        temp.setPicked(true);
        temp.setHeroIndex(heroIndex);
        request.setPlayerName(com.getAccount().getUsername());
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(request);
        }
    }

    public void startPlayingGame() {
        //set initial position for each player and create Hero
        HeroFactory hF = new HeroFactory();
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Cell c = dao.getHeroBeginPosition(player.getSlotIndex());
            player.setPosition(c);
            System.out.println("hero Index:" + player.getHeroIndex());
            player.setHero(hF.createHero(player.getHeroIndex()));
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Cell c = dao.getHeroBeginPosition(player.getSlotIndex());
            player.setPosition(c);
            System.out.println("hero Index:" + player.getHeroIndex());
            player.setHero(hF.createHero(player.getHeroIndex()));
        }
        PlayingGameRequest request = new PlayingGameRequest();
        request.setGame(this);

        //send Game request
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(new GameMatch(this));
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(new GameMatch(this));
        }
    }

    public void initiateCountDown() {
        CountdownThread t = new CountdownThread(this);
        t.start();
    }

    public boolean isFull() {
        return isFull;
    }

    public ArrayList<Player> getTeam1() {
        return team1;
    }

    public ArrayList<Player> getTeam2() {
        return team2;
    }

    public int getCurrentNumOfPlayers() {
        return counter;
    }

    public Player getPlayer(int heroSlotIndex) {
        if (heroSlotIndex < MAX_PLAYER / 2) {
            return team1.get(heroSlotIndex);
        } else {
            return team2.get(heroSlotIndex - (MAX_PLAYER / 2));
        }
    }

    public void drawHeroes(Graphics g, int scrollX, int scrollY) {
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().draw(g, scrollX, scrollY);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().draw(g, scrollX, scrollY);
        }
    }

    public void setGameMap(GameMap gameMap) {
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().setPanel(gameMap);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().setPanel(gameMap);
        }
    }
    public Hero getHeroByCord(int row,int col){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player.getHero();
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player.getHero();
            }
        }
        return null;
    }

    public GameMatch clone() throws CloneNotSupportedException {
        return (GameMatch) super.clone();
    }

}
