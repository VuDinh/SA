package Controllers.listeners;

import View.HeroChoosing.HeroChoosingGUI;
import View.Ingame.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/8/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class BtnPlayListener implements EventHandler<ActionEvent> {
    private HeroChoosingGUI heroChoosingGUI;
    private Game game;
    public BtnPlayListener(HeroChoosingGUI gui,Game game){
        this.game = game;
        this.heroChoosingGUI = gui;

    }
    @Override
    public void handle(ActionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("asdfsdf");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        heroChoosingGUI.getStage().close();
                    }
                });

                game.setVisible(true);
            }
        });

    }
}
