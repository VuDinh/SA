package Controllers.Client;

import Controllers.Communicator;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import View.Login.LoginFrame;
import javafx.application.Platform;
import model.Account;
import model.Message;
import model.Status;

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

    public ClientThread(Account me, Communicator com, LoginFrame login, Game game) {
        this.me = me;
        this.com = com;
        this.login = login;
        this.game = game;
        chatPanel = game.getChatPanel();
    }

    public void addAccountList(ChatPanel panel, List<Account> list) {

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Account acc = (Account) it.next();
            panel.addUser(acc.getUsername());
        }
    }

    public void run() {
        Object o = com.read();

        while (o != null) {
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
            if (o instanceof Account) {
                final Account temp = (Account) o;
                login.setActionText("Logged In successfully!");
                login.setVisible(false);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        if (!temp.getStatus().equals(Status.quit)) {
                            chatPanel.addUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has joined the game!!!");
                        }
                        else{
                            chatPanel.removeUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has left the game!!!");
                        }
                    }
                });
                //Setting up account and hero pos
                if (temp.getStatus().equals(Status.pass)) {
                    me.setUsername(temp.getUsername());
                    game.setVisible(true);
                }

            }
            //invalid account
            if (o instanceof Status) {
                Status s = (Status) o;
                if (s.equals(Status.fail)) {
                    login.setActionText("Invalid username or password!");
                }
            }
            o = com.read();
        }
    }
}
