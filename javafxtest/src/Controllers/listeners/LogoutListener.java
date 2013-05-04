package Controllers.listeners;

import Controllers.Communicator;
import View.Login.LoginFrame;
import View.MainMenu.MainMenuGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.AccountSystem.Status;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/1/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogoutListener implements EventHandler<ActionEvent> {
    private MainMenuGUI mainMenuGUI;
    private Communicator com;
    public LogoutListener(Communicator com,MainMenuGUI mainMenuGUI){
        this.mainMenuGUI=mainMenuGUI;
        this.com=com;
    }
    @Override
    public void handle(ActionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        com.write(Status.quit);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainMenuGUI.getStage().close();
            }
        });
        com.close();
        System.exit(0);
    }
}
