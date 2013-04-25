package Controllers.Server.GameManager;

import Controllers.Requests.CountDownRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 4/16/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountdownThread extends Thread {
    int count;
    private GameMatch gameMatch;
    private ArrayList<Player> team1,team2;
    public CountdownThread(GameMatch gameMatch){
        count=5;
        this.gameMatch = gameMatch;
        this.team1= gameMatch.getTeam1();
        this.team2= gameMatch.getTeam2();
    }
    public void run(){
        Random rad=new Random();

        while(count >= 0){
            CountDownRequest request=new CountDownRequest();
            request.setCount(count);
            for (Iterator it = team1.iterator(); it.hasNext(); ) {
                Player player = (Player) it.next();
                player.getCom().write(request);
                if(count==0 && !player.isPicked()){
                    player.setPicked(true);
                    gameMatch.chooseHero(player.getCom(), player.getSlotIndex(),rad.nextInt(5)+1);
                }
            }
            for (Iterator it = team2.iterator(); it.hasNext(); ) {
                Player player = (Player) it.next();
                player.getCom().write(request);
                if(count==0 && !player.isPicked()){
                    player.setPicked(true);
                    gameMatch.chooseHero(player.getCom(), player.getSlotIndex(),rad.nextInt(5)+1);
                }
            }
            count--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        gameMatch.startPlayingGame();
    }
}
