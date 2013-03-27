package Chat;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/21/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatThread extends Thread {

    Communicator com;
    ChatHandler handler;

    public ChatThread(Communicator com, ChatHandler handler) {
        this.com = com;
        this.handler = handler;
    }

    public void run() {
        Object o = com.read();
        ArrayList<Communicator> coms = handler.getComs();
        while (o != null) {
            Message mess = (Message) o;
            for (Iterator i = coms.iterator(); i.hasNext(); ) {
                Communicator tempCom = (Communicator) i.next();
                if (mess.getStatus().equals(Status.all)) {
                    tempCom.write(mess);
                } else if (mess.getStatus().equals(Status.team) && mess.getSender().getTeam() == tempCom.getAccount().getTeam()) {
                    tempCom.write(mess);
                } else if (mess.getReceiver().getUsername().equals(tempCom.getAccount().getUsername()) && mess.getStatus().equals(Status.def)) {
                    tempCom.write(mess);
                }
            }
            o = com.read();
        }
    }
}
