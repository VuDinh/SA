package Ingame;

import MainMenu.ChatPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

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
    private JComboBox mode = new JComboBox(new String[]{"Team","Alll"});
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
}
