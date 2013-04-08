package Controllers.Client;

import Controllers.Communicator;
import View.HeroChoosing.HeroChoosingGUI;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import View.Login.LoginFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.AccountSystem.Account;
import model.MessageSystem.Message;
import model.AccountSystem.Status;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/1/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientThread extends Thread {
    Account me;
    Communicator com;
    LoginFrame login;
    Game game;
    ChatPanel chatPanel;
    HeroChoosingGUI heroChoosingGUI;

    public ClientThread(Account me, Communicator com, LoginFrame login, HeroChoosingGUI heroChoosingGUI, Game game) {
        this.me = me;
        this.com = com;
        this.login = login;
        this.game = game;
        this.heroChoosingGUI = heroChoosingGUI;
        chatPanel = game.getChatPanel();
    }

    //add all online accounts to the player list
    public void addAccountList(ChatPanel panel, List<Account> list) {

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Account acc = (Account) it.next();
            panel.addUser(acc.getUsername());
        }
    }

    public void run() {
        Object o = com.read();

        while (o != null) {
            //read all messages sent from the server to the current client
            if (o instanceof Message) {
                final Message mes = (Message) o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        chatPanel.addChatMessage(mes.getSender().getUsername(), mes.getContent());
                    }
                });
            }
            //read all accounts connected to the server other than current one
            if (o instanceof List) {
                final List<Account> list = (List<Account>) o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.

                        addAccountList(chatPanel, list);
                    }
                });

            }
            //read account sent from server based on server status
            if (o instanceof Account) {
                final Account temp = (Account) o;
                login.setActionText("Logged In successfully!");
                login.setVisible(false);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        //when someone enters the game
                        if (!temp.getStatus().equals(Status.quit)) {
                            chatPanel.addUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has joined the game!!!");
                        }
                        //when someone leaves the game
                        else {
                            chatPanel.removeUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has left the game!!!");
                        }
                    }
                });
                //Setting up account and hero pos
                if (temp.getStatus().equals(Status.pass)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //To change body of implemented methods use File | Settings | File Templates.
                            Stage stage=new Stage();
                            try {
                                heroChoosingGUI.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    });

                    //To change body of implemented methods use File | Settings | File Templates.
                    me.setUsername(temp.getUsername());
                }

            }
            //invalid account
            if (o instanceof Status) {
                Status s = (Status) o;
                if (s.equals(Status.fail)) {
                    login.setActionText("Invalid username or password!");
                }
                if (s.equals(Status.already)) {
                    login.setActionText("Account already in use!");
                }
            }
            o = com.read();
        }
    }
}
