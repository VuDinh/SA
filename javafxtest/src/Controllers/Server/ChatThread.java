package Controllers.Server;


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
    public ChatThread(AccountDao dao,Communicator com, GameHandler handler) {
        this.com = com;
        this.handler = handler;
       // dao=ctx.getBean("dao",AccountDaoImpl.class);
        this.dao=dao;
    }
    public void setDao(AccountDao dao){
        this.dao=dao;
    }

    public void run() {
        Object o = com.read();
        while (o != null) {
            if (o instanceof Account) {
                Account t = (Account) o;
                if (dao.getAccount(t)!=null) {
                    t.setStatus(Status.pass);
                    com.write(t);
                    com.setAccount(t);
                    List<Account> list=new ArrayList<Account>();
                    for(Iterator it=handler.getComs().iterator();it.hasNext();){
                        Communicator tempCom=(Communicator) it.next();
                        if(tempCom.getAccount()!=com.getAccount()){
                            list.add(tempCom.getAccount());
                            t.setStatus(Status.friend);
                            tempCom.write(t);
                        }
                    }
                    com.write(list);
                } else {
                    System.out.println("fail");
                    com.write(Status.fail);
                }
            }
            if(o instanceof Message){
                Message mes=(Message) o;
                if(mes.getStatus().equals(MessageStatus.all)) sendToAll(mes);
                if(mes.getStatus().equals(MessageStatus.team)) sendToTeam(mes);
                if(mes.getStatus().equals(MessageStatus.def)) sendToOne(mes);
            }
            if(o instanceof Status ){
                Status status=(Status) o;
                if(status.equals(Status.quit)){
                    System.out.println("Quit");
                    break;
                }
            }
            o = com.read();
        }
        announceQuitter();
        com.close();
        handler.removeCom(com);
    }
    public void sendToAll(Message mes){
        for(Iterator it=handler.getComs().iterator();it.hasNext();){
            Communicator tempCom=(Communicator) it.next();
            if(tempCom.getAccount()!=com.getAccount()){
                tempCom.write(mes);
            }
        }
    }
    public void sendToTeam(Message mes){
        for(Iterator it=handler.getComs().iterator();it.hasNext();){
            Communicator tempCom=(Communicator) it.next();
            if(tempCom.getAccount()!=com.getAccount() && tempCom.getAccount().getTeam()==com.getAccount().getTeam()){
                tempCom.write(mes);
            }
        }
    }
    public void sendToOne(Message mes){
        for(Iterator it=handler.getComs().iterator();it.hasNext();){
            Communicator tempCom=(Communicator) it.next();
            if(tempCom.getAccount().getUsername().equals(mes.getReceiver().getUsername())){
                tempCom.write(mes);
            }
        }
    }
    public void announceQuitter(){
        Account acc=new Account(com.getAccount().getUsername(), "unknown",com.getAccount().getTeam());
        acc.setStatus(Status.quit);
        for(Iterator it=handler.getComs().iterator();it.hasNext();){
            Communicator tempCom=(Communicator) it.next();
            if(tempCom.getAccount()!=com.getAccount()){
                tempCom.write(acc);
            }
        }
    }
}
