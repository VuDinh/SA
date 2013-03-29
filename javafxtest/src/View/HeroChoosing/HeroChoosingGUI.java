package View.HeroChoosing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeroChoosingGUI extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("Choosing Hero");
       BorderPane pnlMain=new BorderPane();
       pnlMain.setTop(new HeroChoosingHeader());
       pnlMain.setCenter(new HeroPane());
       Scene scene=new Scene(pnlMain,960,600);
       stage.setScene(scene);
       scene.getStylesheets().add(HeroChoosingGUI.class.getResource("/css/HeroChoosingLayout.css").toExternalForm());
       stage.show();
    }
}
