package View.MainMenu;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Navigation extends VBox {
    public Navigation()
    {
        setId("nav");
        setPadding(new Insets(8));
        setSpacing(20);
        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Online Match"),
                new Hyperlink("Bot Match"),
        };

        for (int i=0; i<2; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            getChildren().add(options[i]);
        }
    }
}
