package Controllers.listeners;


import Controllers.Communicator;
import Controllers.Requests.PlayingGameRequest;
import View.HeroChoosing.HeroChoosingGUI;
import com.sun.corba.se.impl.transport.EventHandlerBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
    public HeroChoosingFactory(HeroChoosingGUI gui,Communicator com){
        this.com=com;
        this.gui=gui;
    }
    public EventHandler<ActionEvent> heroChoosingListener(final int index){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
                int x = index;
                if(index == 7){
                    Random r = new Random();
                    x = r.nextInt(7);
                }
                PlayingGameRequest request=new PlayingGameRequest();
                request.setHeroIndex(x);
                com.write(request);
            }
        };
    }
    public EventHandler<MouseEvent> heroHoveringListener(final int index){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gui.setHoveredHero(index);
            }
        };
    }

    public EventHandler<MouseEvent> skillHoveringListener(final int index){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gui.setHoveredSkill(index);
            }
        };
    }

}
