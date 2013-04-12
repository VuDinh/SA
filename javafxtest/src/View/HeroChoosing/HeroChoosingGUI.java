package View.HeroChoosing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    HeroChoosingHeader header;
    Stage stage;
    AlliedHeroPane alliedHeroPane;
    public static void main(String[] args)
    {
        launch(args);
    }
    public void init(){
        header = new HeroChoosingHeader();
    }
    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
       stage.setTitle("Choosing Hero");
       BorderPane pnlMain=new BorderPane();
       //header= new HeroChoosingHeader();
       pnlMain.setTop(header);
       pnlMain.setCenter(new HeroPane());
       alliedHeroPane=new AlliedHeroPane();
       pnlMain.setLeft(alliedHeroPane);
       Scene scene=new Scene(pnlMain,960,600);
       stage.setResizable(false);
       stage.setScene(scene);
       scene.getStylesheets().add(HeroChoosingGUI.class.getResource("/css/HeroChoosingLayout.css").toExternalForm());
       stage.show();
    }
    public Stage getStage(){
        return stage;
    }
    public void addBtnPlayListener(EventHandler<ActionEvent> e){
        header.addBtnPlayListener(e);
    }
}
