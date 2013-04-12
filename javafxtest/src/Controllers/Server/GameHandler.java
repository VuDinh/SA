package Controllers.Server;
import Controllers.Communicator;
import Controllers.Server.GameManager.GameManager;
import model.AccountSystem.Account;

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
    ArrayList<Account> activeAccounts;
    AccountDao dao;
    Communicator currentCom;
    GameManager gameManager;
    public GameHandler() {
        coms = new ArrayList<Communicator>();
        activeAccounts=new ArrayList<Account>();
        gameManager=new GameManager();
    }

    public void add(Communicator com) {
        coms.add(com);
        currentCom = com;
    }

    public ArrayList<Communicator> getComs() {
        return coms;
    }
    public GameManager getGameManager(){
        return gameManager;
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
    public void addActiveAccount(Account account){
        activeAccounts.add(account);
    }
    public void removeActiveAccount(Account account){
        activeAccounts.remove(account);
    }
    public Account getActiveAccount(Account account){
        for(Iterator it=activeAccounts.iterator();it.hasNext();){
            Account active=(Account) it.next();
            if(active.getUsername().equals(account.getUsername())){
                return active;
            }
        }
        return null;
    }
    @Override
    public void run() {

        /*for (Iterator i = coms.iterator(); i.hasNext(); ) {
            Communicator tempCom = (Communicator) i.next();
            new ChatThread(dao,tempCom,this).start();
        }*/
        new ChatThread(dao,currentCom,this).start();
    }
}
