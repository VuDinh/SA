package View.MainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Account;
import model.MessageStatus;


/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPane extends BorderPane {
    RadioButton radAll;
    RadioButton radTeam;
    RadioButton radDefault;
    TextArea taDialog;
    TextField txtChat;
    ListView<String> lstPlayers;
    public ChatPane()
    {
        //radio Buttons
        VBox vB0=new VBox();
        ToggleGroup tGroup=new ToggleGroup();
        radAll=new RadioButton("All");
        radTeam=new RadioButton("Team");
        radDefault=new RadioButton("Default");
        radAll.setToggleGroup(tGroup);
        radDefault.setToggleGroup(tGroup);
        radTeam.setToggleGroup(tGroup);
        radTeam.setSelected(true);
        vB0.getChildren().addAll(radAll,radTeam,radDefault);
        setLeft(vB0);
        //chat main
        VBox vB=new VBox();
        vB.setSpacing(5);
        setPadding(new Insets(10, 12, 10, 12));
        taDialog=new TextArea();
        txtChat=new TextField();
        taDialog.setPrefColumnCount(100);
        taDialog.setPrefRowCount(6);
        taDialog.setEditable(false);
        vB.getChildren().addAll(taDialog,txtChat);
        setCenter(vB);

        //players name Pane
        VBox vB2=new VBox();
        vB2.setPadding(new Insets(0,0,0,10));
        vB2.setSpacing(10);
        lstPlayers=new ListView<String>();
        lstPlayers.setPrefHeight(190);
        lstPlayers.setPrefWidth(150);
        ObservableList<String> players = FXCollections.observableArrayList();
        lstPlayers.setItems(players);
        vB2.getChildren().add(lstPlayers);
        setRight(vB2);

        taDialog.setId("dialog");
        txtChat.setId("field");
        lstPlayers.setId("list");
        txtChat.setMouseTransparent(false);
        taDialog.setMouseTransparent(false);


    }
    public MessageStatus getSelectedOption(){
        if(radAll.isSelected()){
            return MessageStatus.all;
        }
        if(radTeam.isSelected()){
            return MessageStatus.team;
        }
        if(radDefault.isSelected()){
            return MessageStatus.def;
        }
        return null;
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
    public Account getSelectedAccount(){
     Account account=new Account(lstPlayers.getSelectionModel().getSelectedItem(),"",1);
        return account;
    }
    public void addChatListener(EventHandler<KeyEvent> e){
        txtChat.setOnKeyPressed(e);
    }
    public void addUser(String username){
        lstPlayers.getItems().add(username);
    }
    public void removeUser(String username){
        lstPlayers.getItems().remove(username);
    }
}
