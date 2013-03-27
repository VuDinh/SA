package MainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        VBox vB=new VBox();

        setPadding(new Insets(10, 12, 10, 12));
        TextArea taDialog=new TextArea();
        TextField txtChat=new TextField();
        taDialog.setPrefColumnCount(100);
        taDialog.setPrefRowCount(10);
        vB.getChildren().addAll(taDialog,txtChat);
        setCenter(vB);

        //players name Pane
        VBox vB2=new VBox();
        vB2.setPadding(new Insets(0,0,0,10));
        vB2.setSpacing(10);
        ListView<String> lstPlayers=new ListView<String>();
        lstPlayers.setPrefHeight(190);
        ObservableList<String> players = FXCollections.observableArrayList("longhero3","dota223","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121","scuty121");
        lstPlayers.setItems(players);
        vB2.getChildren().add(lstPlayers);
        setRight(vB2);

    }
}
