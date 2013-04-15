package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.HeroChoosingRequest;
import Controllers.Requests.HeroPickedRequest;
import Controllers.Requests.MatchMakingRequest;
import Controllers.Server.AccountDao;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/11/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    private ArrayList<Communicator> team1;
    private ArrayList<Communicator> team2;
    int counter;
    private boolean isFull;
    private AccountDao dao;
    private static final int MAX_PLAYER = 2;
    private int gameIndex;

    public Game(int index) {
        counter = 0;
        team1 = new ArrayList<Communicator>();
        team2 = new ArrayList<Communicator>();
        isFull = false;
        this.gameIndex = index;
    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }

    public void addPlayer(Communicator com) {
        if (counter < MAX_PLAYER) {
            counter++;
            if (counter < MAX_PLAYER / 2) {
                team1.add(com);
            } else {
                team2.add(com);
            }
            announceMatchRequest();
        }
        if (counter == MAX_PLAYER) {
            isFull = true;
            announcePlayingMatchRequest();
        }

    }

    public void announceMatchRequest() {
        MatchMakingRequest m = new MatchMakingRequest(counter);
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Communicator com = (Communicator) it.next();
            com.write(m);
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Communicator com = (Communicator) it.next();
            com.write(m);
        }
    }

    public void announcePlayingMatchRequest() {
        int count = 0;
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(count);
            Communicator com = (Communicator) it.next();
            com.write(m);
            count++;
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(count);
            Communicator com = (Communicator) it.next();
            com.write(m);
        }
    }

    public void startPlayingGame(Communicator com,int heroSlot, int heroIndex) {
        int count = 0;
        //announce to the others about the other hero picked
        HeroPickedRequest request = new HeroPickedRequest(heroIndex, heroSlot);
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            tempCom.write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            tempCom.write(request);
        }
        //sending needed information for the player to go in game
    }

    public boolean isFull() {
        return isFull;
    }

    public int getCurrentNumOfPlayers() {
        return counter;
    }
}
