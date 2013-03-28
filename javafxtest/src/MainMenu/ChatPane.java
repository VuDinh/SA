package MainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;



/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPane extends BorderPane {
    public ChatPane()
    {
        //radio Buttons
        VBox vB0=new VBox();
        ToggleGroup tGroup=new ToggleGroup();
        RadioButton radAll=new RadioButton("All");
        RadioButton radTeam=new RadioButton("Team");
        RadioButton radDefault=new RadioButton("Default");
        radAll.setToggleGroup(tGroup);
        radDefault.setToggleGroup(tGroup);
        radTeam.setToggleGroup(tGroup);
        vB0.getChildren().addAll(radAll,radTeam,radDefault);
        setLeft(vB0);
        //chat main
        VBox vB=new VBox();
        setPadding(new Insets(10, 12, 10, 12));
        TextArea taDialog=new TextArea();
        TextField txtChat=new TextField();
        taDialog.setPrefColumnCount(100);
        taDialog.setPrefRowCount(6);
        taDialog.setEditable(false);
        vB.getChildren().addAll(taDialog,txtChat);
        setCenter(vB);

        //players name Pane
        VBox vB2=new VBox();
        vB2.setPadding(new Insets(0,0,0,10));
        vB2.setSpacing(10);
        ListView<String> lstPlayers=new ListView<String>();
        lstPlayers.setPrefHeight(190);
        lstPlayers.setPrefWidth(150);
        ObservableList<String> players = FXCollections.observableArrayList("longhero3","dota223","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121");
        lstPlayers.setItems(players);
        vB2.getChildren().add(lstPlayers);
        setRight(vB2);

        taDialog.setId("dialog");
        txtChat.setId("field");
        lstPlayers.setId("list");
        txtChat.setMouseTransparent(false);

    }
}
