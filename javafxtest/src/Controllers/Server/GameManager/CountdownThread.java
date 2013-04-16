package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.CountDownRequest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 4/16/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountdownThread extends Thread {
    int count;
    Communicator com;
    ArrayList<Communicator> team1,team2;
    public CountdownThread(ArrayList<Communicator> team1,ArrayList<Communicator> team2){
        count=30;
        this.team1=team1;
        this.team2=team2;
    }
    public void run(){

        while(count >= 0){
            CountDownRequest request=new CountDownRequest();
            request.setCount(count);
            for (Iterator it = team1.iterator(); it.hasNext(); ) {
                Communicator com = (Communicator) it.next();
                com.write(request);
            }
            for (Iterator it = team2.iterator(); it.hasNext(); ) {
                Communicator com = (Communicator) it.next();
                com.write(request);
            }
            count--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
