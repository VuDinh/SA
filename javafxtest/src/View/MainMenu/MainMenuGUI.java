package View.MainMenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuGUI extends Application {
    Header header;
    FindingMatchPane findingMatchPane;
    ChatPane2 chatPane;
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        stage.setTitle("Control stage");
        BorderPane bP=new BorderPane();
        bP.setId("background");
        header=new Header();
        bP.setTop(header);
        chatPane=new ChatPane2();
        bP.setCenter(chatPane);
        findingMatchPane=new FindingMatchPane();
        bP.setBottom(findingMatchPane);
        Scene scene=new Scene(bP,960,600);
        stage.setScene(scene);
        scene.getStylesheets().add(MainMenuGUI.class.getResource("/css/layout2.css").toExternalForm());
        stage.show();
    }
    public void setPlayer(String text){
        header.setPlayer(text);
    }
    public void addLogoutListener(EventHandler<ActionEvent> e){
        header.addLogoutListener(e);
    }
    public void addSettingListener(EventHandler<ActionEvent> e){
        header.addSettingListener(e);
    }
    public void setStatusText(String text){
        findingMatchPane.setStatusText(text);
    }
    public void addFindMatchListener(EventHandler<ActionEvent> e){
        findingMatchPane.addFindMatchListener(e);
    }

    public String getChatMessage(){
        return chatPane.getChatMessage();
    }
    public TextArea getChatDialog(){
        return chatPane.getChatDialog();
    }
    public void addChatMessage(String sender,String chatMessage){
        chatPane.addChatMessage(sender,chatMessage);
    }
    public void addStatusMessage(String message){
        chatPane.addStatusMessage(message);
    }
    public void addChatListener(EventHandler<KeyEvent> e){
        chatPane.addChatListener(e);
    }
}
