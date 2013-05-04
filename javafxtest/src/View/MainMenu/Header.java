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
import model.AccountSystem.Account;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Header extends BorderPane {
    Button btnLogout;
    Text txtWelcome;
    public Header()
    {
        setId("header");
        setPadding(new Insets(15, 12, 15, 12));
        HBox center=new HBox();
        center.setAlignment(Pos.CENTER_RIGHT);
        btnLogout=new Button("Log out");
        center.getChildren().addAll(btnLogout);

        HBox left=new HBox();
        txtWelcome = new Text("Welcome!");
        txtWelcome.setFont(Font.font("Arial", FontWeight.BOLD,14));

        left.getChildren().add(txtWelcome);
        btnLogout.setPrefSize(100,20);
        setLeft(left);
        setRight(center);

    }
    public void setPlayer(Account account){
        txtWelcome.setText("Welcome,"+account.getUsername()+"!");
    }
    public void addLogoutListener(EventHandler<ActionEvent> e){
        btnLogout.setOnAction(e);
    }
}
