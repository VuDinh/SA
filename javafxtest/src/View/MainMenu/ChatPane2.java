package View.MainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.AccountSystem.Account;
import model.MessageSystem.MessageStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/8/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPane2 extends BorderPane {
    TextArea taDialog;
    TextField txtChat;
    public ChatPane2()
    {
        setId("chatBorder");
        //chat main
        VBox vB=new VBox();
        vB.setSpacing(5);
        setPadding(new Insets(10, 12, 10, 12));
        taDialog=new TextArea();
        txtChat=new TextField();
        taDialog.setPrefColumnCount(100);
        taDialog.setPrefRowCount(8);
        taDialog.setEditable(false);
        vB.getChildren().addAll(taDialog,txtChat);
        setCenter(vB);

        //players name Pane
        VBox vB2=new VBox();
        vB2.setPadding(new Insets(0,0,0,10));
        vB2.setSpacing(10);
        setRight(vB2);
        taDialog.setId("dialog");
        txtChat.setId("field");
        txtChat.setMouseTransparent(false);
        taDialog.setMouseTransparent(false);


    }
    public String getChatMessage(){
        return txtChat.getText();
    }
    public TextArea getChatDialog(){
        return taDialog;
    }
    public void addChatMessage(String sender,String chatMessage){
        taDialog.appendText(sender+":"+chatMessage+"\n");
        txtChat.clear();
    }
    public void addStatusMessage(String message){
        taDialog.appendText(message+"\n");
    }

    public void addChatListener(EventHandler<KeyEvent> e){
        txtChat.setOnKeyPressed(e);
    }
}
