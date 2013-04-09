package View.MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Header extends BorderPane {
    Button btnLogout;
    Button btnSettings;
    Text txtWelcome;
    public Header()
    {
        setId("header");
        setPadding(new Insets(15, 12, 15, 12));
        HBox center=new HBox();
        center.setSpacing(12);
        center.setAlignment(Pos.CENTER);
        btnLogout=new Button("Log out");
        btnSettings=new Button("Settings");
        center.getChildren().addAll(btnLogout,btnSettings);

        HBox left=new HBox();
        txtWelcome = new Text("Welcome!");
        txtWelcome.setFont(Font.font("Arial", FontWeight.BOLD,14));

        left.getChildren().add(txtWelcome);
        btnLogout.setPrefSize(100,20);
        btnSettings.setPrefSize(100,20);
        setLeft(left);
        setCenter(center);

    }
    public void setPlayer(String text){
        txtWelcome.setText(text);
    }
    public void addLogoutListener(EventHandler<ActionEvent> e){
        btnLogout.setOnAction(e);
    }
    public void addSettingListener(EventHandler<ActionEvent> e){
        btnSettings.setOnAction(e);
    }
}
