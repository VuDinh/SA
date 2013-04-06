package Controllers.listeners;

import Controllers.Communicator;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.AccountSystem.Account;
import model.MessageSystem.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/30/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatListener implements EventHandler<KeyEvent> {
    Game game;
    Communicator com;
    Account me;
    public ChatListener(Communicator com,Game game,Account me){
        this.game=game;
        this.com=com;
        this.me=me;
    }
    //sending messsage to the server when the player press enter in the text field
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            ChatPanel chatPanel=game.getChatPanel();
            Message mess=new Message(new Account(me.getUsername(),me.getPassword(), me.getTeam()),
                    chatPanel.getSelectedAccount(),chatPanel.getChatMessage());
            mess.setStatus(chatPanel.getSelectedOption());
            System.out.println(me.getUsername());
            chatPanel.addChatMessage(me.getUsername(),chatPanel.getChatMessage());
            com.write(mess);
        }
    }
}
