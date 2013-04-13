package Chat;

import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/20/13
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatHandler implements Runnable {
    ArrayList<Communicator> coms;
    PriorityQueue<Message> mgs;

    public ChatHandler() {
        coms = new ArrayList<Communicator>();
        mgs = new PriorityQueue<Message>();

    }

    public void add(Communicator com) {
        coms.add(com);
    }

    public ArrayList<Communicator> getComs() {
        return coms;
    }

    public void setComs(ArrayList<Communicator> coms) {
        this.coms = coms;
    }

    @Override
    public void run() {
            for (Iterator i = coms.iterator(); i.hasNext(); ) {
                Communicator tempCom = (Communicator) i.next();
                new ChatThread(tempCom,this).start();
            }
    }
}
