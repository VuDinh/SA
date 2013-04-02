package Controllers.Server;
import Controllers.Communicator;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/31/13
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameHandler extends Thread {
    ArrayList<Communicator> coms;
    AccountDao dao;
    Communicator currentCom;
    public GameHandler() {
        coms = new ArrayList<Communicator>();

    }

    public void add(Communicator com) {
        coms.add(com);
        currentCom = com;
    }

    public ArrayList<Communicator> getComs() {
        return coms;
    }
    public void removeCom(Communicator com){
        coms.remove(com);
    }
    public void setComs(ArrayList<Communicator> coms) {
        this.coms = coms;
    }
    public void setDao(AccountDao dao){
        this.dao =dao;
    }
    @Override
    public void run() {

        System.out.println(coms.size());
        /*for (Iterator i = coms.iterator(); i.hasNext(); ) {
            Communicator tempCom = (Communicator) i.next();
            new ChatThread(dao,tempCom,this).start();
        }*/
        new ChatThread(dao,currentCom,this).start();
    }
}
