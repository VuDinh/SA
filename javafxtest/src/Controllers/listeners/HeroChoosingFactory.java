package Controllers.listeners;


import Controllers.Communicator;
import Controllers.Requests.PlayingGameRequest;
import View.HeroChoosing.HeroChoosingGUI;
import View.MainMenu.ChatPane2;
import com.sun.corba.se.impl.transport.EventHandlerBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.MessageSystem.Message;
import model.MessageSystem.MessageStatus;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/13/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroChoosingFactory {
    Communicator com;
    int index;
    HeroChoosingGUI gui;

    public HeroChoosingFactory(HeroChoosingGUI gui, Communicator com) {
        this.com = com;
        this.gui = gui;
    }

    public EventHandler<ActionEvent> heroChoosingListener(final int index) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
                int x = index;
                if (index == 7) {
                    Random r = new Random();
                    x = r.nextInt(7);
                }
                PlayingGameRequest request = new PlayingGameRequest();
                request.setHeroIndex(x);
                request.setHeroSlot(gui.getFacade().getHeroSlot());
                request.setMatchIndex(gui.getFacade().getGameIndex());
                com.write(request);
            }
        };
    }

    public EventHandler<MouseEvent> heroHoveringListener(final int index) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gui.setHoveredHero(index);
            }
        };
    }

    public EventHandler<MouseEvent> skillHoveringListener(final int index) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gui.setHoveredSkill(index);
            }
        };
    }

    public EventHandler<KeyEvent> chatListener() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER){
                    System.out.println("haha");
                    ChatPane2 chatPanel=gui.getChatPane();
                    Message mess=new Message(gui.getFacade().getClientAccount(),
                            null,chatPanel.getChatMessage());
                    mess.setStatus(MessageStatus.team);
                    mess.setGameIndex(gui.getFacade().getGameIndex());
                    mess.setSlotIndex(gui.getFacade().getHeroSlot());
                    chatPanel.addChatMessage(gui.getFacade().getUsername(),chatPanel.getChatMessage());
                    com.write(mess);
                }
            }
        };
    }

}
