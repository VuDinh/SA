package Controllers.listeners;

import Controllers.Communicator;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Account;
import model.Message;

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
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            ChatPanel chatPanel=game.getChatPanel();
            Message mess=new Message(new Account(me.getUsername(),me.getPassword(), me.getTeam()),chatPanel.getSelectedAccount(),chatPanel.getChatMessage());
            mess.setStatus(chatPanel.getSelectedOption());
            chatPanel.addChatMessage(game.getAccount().getUsername(),chatPanel.getChatMessage());
            com.write(mess);
        }
    }
}
