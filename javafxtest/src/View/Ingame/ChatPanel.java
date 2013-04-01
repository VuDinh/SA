package View.Ingame;

import View.MainMenu.ChatPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import model.Account;
import model.MessageStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.TextArea;
import java.awt.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/22/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPanel extends JFXPanel {

    private TextArea chatTxt = new TextArea();
    private TextField chatField = new TextField();
    private JComboBox mode = new JComboBox(new String[]{"Team","All"});
    ChatPane chatPane;
    public ChatPanel(){
        setLayout(new BorderLayout());
        chatPane=new ChatPane();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene=new Scene(chatPane, 500,150);
                setScene(scene);
                scene.getStylesheets().add(ChatPanel.class.getResource("/css/chatLayout.css").toExternalForm());
            }
        });
    }
    public MessageStatus getSelectedOption(){
        return chatPane.getSelectedOption();
    }
    public String getChatMessage(){
        return chatPane.getChatMessage();
    }
    public javafx.scene.control.TextArea getChatDialog(){
        return chatPane.getChatDialog();
    }
    public void addChatMessage(String sender,String chatMessage){
        chatPane.addChatMessage(sender,chatMessage);
    }
    public void addStatusMessage(String message){
        chatPane.addStatusMessage(message);
    }
    public Account getSelectedAccount(){
        return chatPane.getSelectedAccount();
    }
    public void addChatListener(EventHandler<KeyEvent> e){
        chatPane.addChatListener(e);
    }
    public void addUser(String username){
        chatPane.addUser(username);
    }
    public void removeUser(String username){
        chatPane.removeUser(username);
    }
}
