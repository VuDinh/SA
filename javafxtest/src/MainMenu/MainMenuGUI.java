package MainMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.text.NavigationFilter;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuGUI extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        stage.setTitle("Control stage");
        BorderPane bP=new BorderPane();

        bP.setTop(new Header());
        bP.setLeft(new Navigation());
        bP.setRight(new ChatPane());
        Scene scene=new Scene(bP,960,600);
        stage.setScene(scene);
        scene.getStylesheets().add(MainMenuGUI.class.getResource("/css/layout2.css").toExternalForm());
        stage.show();
    }
}
