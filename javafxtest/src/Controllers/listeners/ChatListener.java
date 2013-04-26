package Controllers.listeners;

import Controllers.Communicator;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.AccountSystem.Account;
import model.Facade.Facade;
import model.MessageSystem.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/30/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatListener implements EventHandler<KeyEvent> {
    private Game game;
    private Communicator com;
    private Facade facade;
    public ChatListener(Communicator com,Game game,Facade facade){
        this.game=game;
        this.com=com;
        this.facade=facade;
    }
    //sending messsage to the server when the player press enter in the text field
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            ChatPanel chatPanel=game.getChatPanel();
            Message mess=new Message(facade.getClientAccount(),
                    chatPanel.getSelectedAccount(),chatPanel.getChatMessage());
            mess.setStatus(chatPanel.getSelectedOption());
            System.out.println(facade.getUsername());
            chatPanel.addChatMessage(facade.getUsername(),chatPanel.getChatMessage());
            com.write(mess);
        }
    }
}
