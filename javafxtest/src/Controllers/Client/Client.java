package Controllers.Client;

import Controllers.Communicator;
import Controllers.listeners.ChatListener;
import Controllers.listeners.LoginListener;
import Controllers.listeners.MapListener;
import Controllers.listeners.ScrollListener;
import Utilities.Utilizer;
import View.Login.LoginFrame;
import model.AccountSystem.Account;
import View.Ingame.Game;
import model.HeroSystem.HeroFactory;
import model.AccountSystem.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 3/28/13
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Client implements Runnable {
    Account me;
    Game inGame;
    LoginFrame login;
    Communicator com;
    int port;
    String host;
    private static ApplicationContext ctx;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void execute() {
        initalize();
        addListeners();
        settingup();
    }

    public void initalize() {
        Utilizer.load();

        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            com = new Communicator(socket, ois, oos);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void addListeners() {
        //delete later
        Account acc = new Account("long", "123", 1);
        HeroFactory hf = new HeroFactory();
        acc.setHero(hf.createHero(1));
        me = acc;
        inGame = new Game(me);
        login = new LoginFrame(me);
        MapListener mapListener = new MapListener(inGame.getGameMap());
        ScrollListener scrollListener = new ScrollListener(inGame.getGameMap());
        ChatListener chatListener = new ChatListener(com, inGame, me);
        LoginListener loginListener = new LoginListener(com, login, inGame, me);
        login.addLoginListener(loginListener);
        inGame.getChatPanel().addChatListener(chatListener);
        inGame.getGameMap().addKeyListener(scrollListener);
        inGame.getGameMap().addMouseListener(mapListener);
        inGame.getGameMap().addMouseMotionListener(mapListener);
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.

                com.close();
                login.dispose();
                System.exit(0);
            }

        });
        inGame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                com.write(Status.quit);
                com.close();
                inGame.dispose();
                System.exit(0);
            }

        });

    }

    public void settingup() {
        login.setVisible(true);
        ClientThread t = new ClientThread(me, com, login, inGame);
        t.start();
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("client-context.xml");
        Client con = ctx.getBean("client", Client.class);
        con.execute();
    }

    public void run() {

    }
}
