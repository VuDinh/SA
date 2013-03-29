package View.MainMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Header extends HBox {
    public Header()
    {
        setId("header");
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(12);
        Button btnLogout=new Button("Log out");
        Button btnSettings=new Button("Settings");
        setAlignment(Pos.CENTER);
        btnLogout.setPrefSize(100,20);
        btnSettings.setPrefSize(100,20);
        getChildren().addAll(btnLogout,btnSettings);
    }
}
