package Chat;

import model.Account;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/20/13
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server implements Runnable {
    AccountList accounts;
    public static void main(String args[]){
        Server server=new Server();
        new Thread(server).start();
    }
    public Server(){
        accounts=new AccountList();
        accounts.addAccount(new Account("long","123",1));
        accounts.addAccount(new Account("hero","2345",1));
        accounts.addAccount(new Account("baba","123123",2));
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        try {
            ServerSocket s=new ServerSocket(2400);
            while(true){
                System.out.println("Waiting");
                ChatHandler handler=new ChatHandler();
                for(Iterator i=accounts.list.iterator();i.hasNext();)  {
                    Socket socket=s.accept();
                    Account temp=(Account) i.next();
                    System.out.println(temp + ":");
                    Communicator com=new Communicator(socket, new ObjectInputStream(socket.getInputStream()),new ObjectOutputStream(socket.getOutputStream()));
                    com.write(temp);
                    com.write(accounts);
                    com.setAccount(temp);
                    handler.add(com);
                }
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
