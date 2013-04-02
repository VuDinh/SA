package View.Login;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.Account;

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
    Account me;
    public LoginFrame(Account me){
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
                        Scene scene=new Scene(loginPane,300,300);
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
        setSize(300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
