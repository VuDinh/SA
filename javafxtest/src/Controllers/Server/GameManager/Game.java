package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.HeroChoosingRequest;
import Controllers.Requests.MatchMakingRequest;

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
    private static final int MAX_PLAYER = 2;

    public Game() {
        counter = 0;
        team1 = new ArrayList<Communicator>();
        team2 = new ArrayList<Communicator>();
        isFull = false;
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
        if(counter==MAX_PLAYER){
            isFull=true;
            announcePlayingMatchRequest();
        }

    }
    public void announceMatchRequest(){
        MatchMakingRequest m=new MatchMakingRequest(counter);
        for(Iterator it=team1.iterator();it.hasNext();){
            Communicator com=(Communicator)it.next();
            com.write(m);
        }
        for(Iterator it=team2.iterator();it.hasNext();){
            Communicator com=(Communicator)it.next();
            com.write(m);
        }
    }
    public void announcePlayingMatchRequest(){
        //setHero Position for each hero in the com
        HeroChoosingRequest m=new HeroChoosingRequest();
        for(Iterator it=team1.iterator();it.hasNext();){
            Communicator com=(Communicator)it.next();
            com.write(m);
        }
        for(Iterator it=team2.iterator();it.hasNext();){
            Communicator com=(Communicator)it.next();
            com.write(m);
        }
        //set
    }
    public boolean isFull(){
        return isFull;
    }
    public int getCurrentNumOfPlayers(){
        return counter;
    }
}
