package View.Login;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.AccountSystem.Account;
import View.Login.LoginFrame;
import model.Facade.Facade;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 3/28/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginFrame extends JFrame {
    LoginPane loginPane;
    JFXPanel tempPane;
    Facade me;
    public LoginFrame(Facade me){
        this.me=me;
        setTitle("Login");
        loginPane=new LoginPane();
        tempPane=new JFXPanel();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Scene scene=new Scene(loginPane,475,280);
                        scene.getStylesheets().add(LoginFrame.class.getResource("/css/layout1.css").toExternalForm());
                        tempPane.setScene(scene);
                    }
                });
                add(tempPane);
            }
        });
        setDefaultProperties();
    }
    public String getUsername(){
        return loginPane.getUsername();
    }
    public String getPassword(){
        return loginPane.getPassword();
    }
    public void addLoginListener(EventHandler<ActionEvent> event){
        loginPane.addLoginListener(event);
    }
    public void setActionText(String text){
        loginPane.setActionText(text);
    }
    public void setDefaultProperties(){
        setSize(475,280);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
