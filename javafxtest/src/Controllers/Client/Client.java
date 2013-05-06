package Controllers.Client;

import Controllers.Communicator;
import Controllers.listeners.*;
import Utilities.Utilizer;
import View.HeroChoosing.HeroChoosingGUI;
import View.Login.LoginFrame;
import View.MainMenu.MainMenuGUI;
import javafx.application.Platform;
import model.AccountSystem.Account;
import View.Ingame.Game;
import model.Facade.Facade;
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
    //Account me;
    Game inGame;
    LoginFrame login;
    HeroChoosingGUI heroChoosingGUI;
    Communicator com;
    MainMenuGUI mainMenuGUI;
    int port;
    String host;
    Facade facade;
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
        settingUp();
    }
    //Connect to the server
    public void initalize() {
        Utilizer.load();

        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            com = new Communicator(socket, ois, oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        facade=new Facade();
        Account acc = new Account("long", "123", 1);
        HeroFactory hf = new HeroFactory();
        //initialize GUI
        inGame = new Game(facade);
        login = new LoginFrame(facade);
        mainMenuGUI=new MainMenuGUI();
        mainMenuGUI.setFacade(facade);
        mainMenuGUI.init();
        heroChoosingGUI = new HeroChoosingGUI();
        heroChoosingGUI.setFacade(facade);
        heroChoosingGUI.init();
    }
    //add all needed listeners to all the frames
    public void addListeners() {
        //delete later
        MapListener mapListener = new MapListener(com,inGame.getGameMap(),inGame.getControlPanel());
        ScrollListener scrollListener = new ScrollListener(com,inGame.getGameMap());
        ChatListener chatListener = new ChatListener(com, inGame, facade);
        LoginListener loginListener = new LoginListener(com, login);
        ControlListener controlListener = new ControlListener(inGame.getGameMap(),inGame.getControlPanel());


        mainMenuGUI.addChatListener(new BroadcastChatListener(mainMenuGUI,com));
        mainMenuGUI.addLogoutListener(new LogoutListener(com,mainMenuGUI));
        mainMenuGUI.addFindMatchListener(new FindingMatchListener(mainMenuGUI,com));

        HeroChoosingFactory heroChoosingFactory=new HeroChoosingFactory(heroChoosingGUI,com);
        heroChoosingGUI.addHeroChoosingListener(heroChoosingFactory);
        heroChoosingGUI.addHeroHoveringListener(heroChoosingFactory);

        login.addLoginListener(loginListener);

        inGame.getChatPanel().addChatListener(chatListener);
        inGame.getGameMap().addKeyListener(scrollListener);
        inGame.getGameMap().addMouseListener(mapListener);
        inGame.getGameMap().addMouseMotionListener(mapListener);
        inGame.getControlPanel().addMouseListener(controlListener);
        inGame.getControlPanel().addMouseMotionListener(controlListener);
        //sending quitting status to the server when closing
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                com.close();
                login.dispose();
                System.exit(0);
            }

        });
        inGame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                com.write(Status.quit);
                com.close();
                inGame.dispose();
                System.exit(0);
            }

        });

    }
    //running client thread
    public void settingUp() {
        login.setVisible(true);
        ClientThread t = new ClientThread(facade, com, login,mainMenuGUI,heroChoosingGUI, inGame);
        t.start();
    }
    //execute all actions
    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("client-context.xml");
        Client con = ctx.getBean("client", Client.class);
        con.execute();
    }

    public void run() {

    }
}
