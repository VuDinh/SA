package Controllers.listeners;

import Controllers.Communicator;
import View.MainMenu.MainMenuGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.AccountSystem.Status;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/10/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindingMatchListener implements EventHandler<ActionEvent> {
    Communicator com;
    MainMenuGUI mainMenuGUI;
    public FindingMatchListener(MainMenuGUI mainMenuGUI,Communicator com){
        this.mainMenuGUI=mainMenuGUI;
        this.com=com;
    }
    @Override
    public void handle(ActionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("finding");
        com.write(Status.findingMatch);
    }
}
