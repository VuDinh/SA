package Controllers.Server;

import Controllers.Communicator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/31/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server  {
    private ServerSocket serverSocket;
    static ApplicationContext ctx;
    private DbUtilities db;
    private static AccountDao dao;
    public static void main(String[] args){
        ctx= new ClassPathXmlApplicationContext("server-context.xml");
        Server s=ctx.getBean("server",Server.class);
        dao=ctx.getBean("dao",AccountDaoImpl.class);
        s.run();
    }
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setDb(DbUtilities db) {
        this.db = db;
    }

    public void run() {
        //create database
        db.createDb();
        System.out.println(" database created ");
        GameHandler handler=ctx.getBean("gameHandler",GameHandler.class);
        //To change body of implemented methods use File | Settings | File Templates.
        while(true){
            try {
                System.out.println("Waiting");
                Socket client = serverSocket.accept();
                System.out.println("Connected");

                Communicator com=new Communicator(client,new ObjectInputStream(client.getInputStream())
                        ,new ObjectOutputStream(client.getOutputStream()));
<<<<<<< HEAD
=======
                handler.setDao(dao);
                //adding communicator to the handler
>>>>>>> af220c489ce7954e48a8a1a1d94a7a352cd4e9c0
                handler.add(com);
                //starting handler thread
                new Thread(handler).start();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
