package Controllers.Server;


import Controllers.Requests.HeroAttackRequest;
import Controllers.Requests.HeroMoveRequest;
import Controllers.Requests.PlayingGameRequest;
import model.MessageSystem.Message;
import model.MessageSystem.MessageStatus;
import model.AccountSystem.Status;
import Controllers.Communicator;
import model.AccountSystem.Account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/21/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatThread extends Thread {

    Communicator com;
    GameHandler handler;
    AccountDao dao;

    //static ApplicationContext ctx=new ClassPathXmlApplicationContext("server-context.xml");;
    public ChatThread(AccountDao dao, Communicator com, GameHandler handler) {
        this.com = com;
        this.dao = dao;
        this.handler = handler;
        // dao=ctx.getBean("dao",AccountDaoImpl.class);
    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }

    //run the client thread
    public void run() {
        Object o = com.read();
        while (o != null) {
            if (o instanceof Account) {
                Account t = (Account) o;
                //check if it is a valid account
                if (dao.getAccount(t) != null && handler.getActiveAccount(t) == null) {
                    t.setStatus(Status.pass);
                    com.write(t);
                    com.setAccount(t);
                    handler.addActiveAccount(t);
                    List<Account> list = new ArrayList<Account>();
                    //acknowledge the others about the current account
                    for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
                        Communicator tempCom = (Communicator) it.next();
                        if (tempCom.getAccount() != null)
                            if (tempCom.getAccount() != com.getAccount()) {
                                list.add(tempCom.getAccount());
                                t.setStatus(Status.friend);
                                tempCom.write(t);
                            }
                    }
                    if (!list.isEmpty())
                        com.write(list);
                } else {
                    if (dao.getAccount(t) == null)
                        com.write(Status.fail);
                    else
                        com.write(Status.already);
                    o = com.read();
                    continue;
                }
            }

            //sending message
            if (o instanceof Message) {
                Message mes = (Message) o;
                if (mes.getStatus().equals(MessageStatus.all)) sendToAll(mes);
                if (mes.getStatus().equals(MessageStatus.team)) sendToTeam(mes);
                if (mes.getStatus().equals(MessageStatus.def)) sendToOne(mes);
                if (mes.getStatus().equals(MessageStatus.broadcast)) sendBroadCast(mes);
            }

            if(o instanceof PlayingGameRequest){
                PlayingGameRequest request=(PlayingGameRequest) o;
                handler.getGameManager().chooseHero(com, request);
            }
            if(o instanceof HeroMoveRequest){
                HeroMoveRequest request=(HeroMoveRequest) o;
                handler.getGameManager().handleHeroMoveRequest(request);
            }
            if(o instanceof HeroAttackRequest){
                HeroAttackRequest request=(HeroAttackRequest) o;
                handler.getGameManager().handleHeroAttackRequest(request);
            }

            if (o instanceof Status) {
                Status status = (Status) o;
                //findingMatch action
                if(status.equals(Status.findingMatch)){
                    handler.getGameManager().addPlayer(com);
                }
                //quitting action
                if (status.equals(Status.quit)) {
                    System.out.println("Quit");
                    break;
                }
            }
            o = com.read();
        }
        //inform to the others about the quitter
        announceQuitter();
        com.close();
        handler.removeActiveAccount(com.getAccount());
        handler.removeCom(com);
    }


    //sending message to all
    public void sendToAll(Message mes) {
        for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            if (tempCom.getAccount() != com.getAccount()) {
                tempCom.write(mes);
            }
        }
    }
    //sending to the team
    public void sendToTeam(Message mes) {
        for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            if (tempCom.getAccount() != com.getAccount() && tempCom.getAccount().getTeam() == com.getAccount().getTeam()) {
                tempCom.write(mes);
            }
        }
    }
    //sending to the one
    public void sendToOne(Message mes) {
        for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            if (tempCom.getAccount().getUsername().equals(mes.getReceiver().getUsername())) {
                tempCom.write(mes);
            }
        }
    }
    //warn the others about quitters
    public void announceQuitter() {
        if (com.getAccount() != null) {
            Account acc = new Account(com.getAccount().getUsername(), "unknown", com.getAccount().getTeam());
            acc.setStatus(Status.quit);
            for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
                Communicator tempCom = (Communicator) it.next();
                if (tempCom.getAccount() != com.getAccount()) {
                    tempCom.write(acc);
                }
            }
        }
    }
    public void sendBroadCast(Message mes){
        for (Iterator it = handler.getComs().iterator(); it.hasNext(); ) {
            Communicator tempCom = (Communicator) it.next();
            if (tempCom.getAccount() != com.getAccount()) {
                tempCom.write(mes);
            }
        }
    }
}
