package Controllers.listeners;

import Controllers.Communicator;
import View.Ingame.Game;
import View.Login.LoginFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.Account;




/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/30/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginListener implements EventHandler<ActionEvent> {

    LoginFrame loginFrame;
    Game inGame;
    Communicator com;
    Account me;
    public LoginListener(Communicator com,LoginFrame loginFrame,Game inGame,Account me){
        this.com=com;
        this.loginFrame=loginFrame;
        this.inGame = inGame;
        this.me=me;
    }

    @Override
    public void handle(ActionEvent e) {
        /*loginFrame.setVisible(false);
        inGame.setVisible(true);*/
        loginFrame.setActionText("Signing In ...");
        System.out.println(loginFrame.getUsername());
        com.write(new Account(loginFrame.getUsername(),loginFrame.getPassword(),0));
        //valid account
    }
}