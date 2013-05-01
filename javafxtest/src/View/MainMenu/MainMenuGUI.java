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
import javafx.stage.StageStyle;
import model.AccountSystem.Account;
import model.Facade.Facade;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuGUI extends Application {
    private Header header;
    private FindingMatchPane findingMatchPane;
    private ChatPane2 chatPane;
    private Stage stage;
    private Facade facade;
    public static void main(String[] args)
    {
        launch(args);
    }
    public void init(){
        header = new Header();
        findingMatchPane=new FindingMatchPane();
        chatPane=new ChatPane2();
    }
    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Control stage");
        BorderPane bP=new BorderPane();
        bP.setId("background");
        bP.setTop(header);
        bP.setCenter(chatPane);
        bP.setBottom(findingMatchPane);
        Scene scene=new Scene(bP,960,600);
        stage.setScene(scene);
        scene.getStylesheets().add(MainMenuGUI.class.getResource("/css/layout2.css").toExternalForm());
        stage.show();
    }
    public Stage getStage(){
        return stage;
    }
    public void setFacade(Facade facade){
        this.facade=facade;
    }
    public Facade getFacade(){
        return facade;
    }
    public void setPlayer(Account account){
        header.setPlayer(account);
    }
    public void addLogoutListener(EventHandler<ActionEvent> e){
        header.addLogoutListener(e);
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
        System.out.println("added listener");
        chatPane.addChatListener(e);
    }
    public Header getHeader(){
        return header;
    }
    public ChatPane2 getChatPane(){
        return chatPane;
    }
    public void disableBtnFindingMatch(){
        findingMatchPane.disableBtnFindingMatch();
    }
    public void enableBtnFindingMatch(){
        findingMatchPane.enableBtnFindingMatch();
    }
    public void setPlayerNumStatus(int num){
        findingMatchPane.setPlayerNumStatus(num);
    }

}
