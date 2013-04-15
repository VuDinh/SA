package View.HeroChoosing;

import Controllers.listeners.HeroChoosingFactory;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Facade.Facade;

import java.awt.image.BufferedImage;

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
    HeroPane heroPane;
    Facade facade;
    public static void main(String[] args)
    {
        launch(args);
    }
    public void init(){
        header = new HeroChoosingHeader();
        heroPane = new HeroPane(facade);
        alliedHeroPane=new AlliedHeroPane();
    }
    public void setFacade(Facade facade){
        this.facade=facade;
    }
    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
       stage.setTitle("Choosing Hero");
       BorderPane pnlMain=new BorderPane();
       pnlMain.setTop(header);
       pnlMain.setCenter(heroPane);
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
    public void setCountDownTime(int t){
        header.setCountdownTime(t);
    }
    public void addHeroChoosingListener(HeroChoosingFactory factory){
        heroPane.addHeroChoosingListener(factory);
    }
    public void addHeroHoveringListener(HeroChoosingFactory heroChoosingFactory){
       heroPane.addHeroHoveringListener(heroChoosingFactory);
    }
    public void setHoveredHero(int index){
        heroPane.setHoveredHero(index);
    }
    public void setHoveredSkill(int index){
        heroPane.setHoveredSkill(index);
    }
    public void setAllyUnknown(int index){
        alliedHeroPane.setAllyUnknown(index);
    }
    public void setAllyIcon(int index,BufferedImage img){
        alliedHeroPane.setAllyIcon(index, img);
    }
}
