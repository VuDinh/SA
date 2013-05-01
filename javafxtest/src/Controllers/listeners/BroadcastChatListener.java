package Controllers.listeners;

import Controllers.Communicator;
import View.MainMenu.ChatPane2;
import View.MainMenu.MainMenuGUI;
import com.sun.jmx.snmp.daemon.CommunicatorServer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MessageSystem.Message;
import model.MessageSystem.MessageStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/10/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastChatListener implements EventHandler<KeyEvent> {
    private MainMenuGUI mainMenuGUI;
    private Communicator com;
    public BroadcastChatListener(MainMenuGUI mainMenuGUI,Communicator com){
        this.com=com;
        this.mainMenuGUI=mainMenuGUI;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("pressed");
        if(keyEvent.getCode() == KeyCode.ENTER){
            System.out.println("haha");
            ChatPane2 chatPanel=mainMenuGUI.getChatPane();
            Message mess=new Message(mainMenuGUI.getFacade().getClientAccount(),
                    null,chatPanel.getChatMessage());
            mess.setStatus(MessageStatus.broadcast);
            chatPanel.addChatMessage(mainMenuGUI.getFacade().getUsername(),chatPanel.getChatMessage());
            com.write(mess);
        }
    }
}
