package Controllers.Client;

import Controllers.Communicator;
import Controllers.Requests.*;
import Controllers.Server.GameManager.GameMatch;
import View.HeroChoosing.HeroChoosingGUI;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import View.Login.LoginFrame;
import View.MainMenu.MainMenuGUI;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.AccountSystem.Account;
import model.Facade.Facade;
import model.HeroSystem.HeroStatus;
import model.MessageSystem.Message;
import model.AccountSystem.Status;
import model.MessageSystem.MessageStatus;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/1/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientThread extends Thread {
    Communicator com;
    LoginFrame login;
    Game game;
    ChatPanel chatPanel;
    HeroChoosingGUI heroChoosingGUI;
    MainMenuGUI mainMenuGUI;
    Facade facade;

    public ClientThread(Facade facade, Communicator com, LoginFrame login, MainMenuGUI mainMenuGUI, HeroChoosingGUI heroChoosingGUI, Game game) {
        this.com = com;
        this.login = login;
        this.game = game;
        this.heroChoosingGUI = heroChoosingGUI;
        this.mainMenuGUI = mainMenuGUI;
        chatPanel = game.getChatPanel();
        this.facade = facade;
    }

    //add all online accounts to the player list
    public void addAccountList(ChatPanel panel, List<Account> list) {

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Account acc = (Account) it.next();
            panel.addUser(acc.getUsername());
        }
    }

    public void run() {
        Object o = com.read();

        while (o != null) {
            //read all messages sent from the server to the current client
            if (o instanceof Message) {
                final Message mes = (Message) o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        if (mes.getStatus().equals(MessageStatus.broadcast) && mainMenuGUI.getStage().isShowing()) {
                            mainMenuGUI.getChatPane().addChatMessage(mes.getSender().getUsername(), mes.getContent());
                        }
                        if (mes.getStatus().equals(MessageStatus.team) && heroChoosingGUI.getStage().isShowing()) {
                            heroChoosingGUI.getChatPane().addChatMessage(mes.getSender().getUsername(), mes.getContent());
                        }
                        if (game.isVisible()) chatPanel.addChatMessage(mes.getSender().getUsername(), mes.getContent());
                    }
                });
            }
            //read all accounts connected to the server other than current one
            else if (o instanceof List) {
                final List<Account> list = (List<Account>) o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.

                        addAccountList(chatPanel, list);
                    }
                });

            }
            //read account sent from server based on server status
            else if (o instanceof Account) {
                final Account temp = (Account) o;
                login.setActionText("Logged In successfully!");
                login.setVisible(false);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //when someone enters the game
                        if (!temp.getStatus().equals(Status.quit)) {
                            chatPanel.addUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has joined the game!!!");
                        }
                        //when someone leaves the game
                        else {
                            chatPanel.removeUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has left the game!!!");
                        }
                    }
                });
                //Setting up account and hero pos
                if (temp.getStatus().equals(Status.pass)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            login.setVisible(false);
                            Stage stage = new Stage();
                            mainMenuGUI.start(stage);
                            mainMenuGUI.setPlayer(temp);
                        }
                    });
                    facade.setUsername(temp.getUsername());
                }
            } else if (o instanceof MatchMakingRequest) {
                final MatchMakingRequest mR = (MatchMakingRequest) o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainMenuGUI.setPlayerNumStatus(mR.getPlayerNum());
                        mainMenuGUI.disableBtnFindingMatch();
                    }
                });

            } else if (o instanceof GameMatch) {
                GameMatch pR = (GameMatch) o;
                System.out.println("Player number:" + pR.getPlayer(0).getHero().getY());
                facade.setMatch(pR);
                if (facade.getHeroSlot() == pR.getTurnIndex()) facade.setIsLocked(false);
                game.setInitialProperties();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        heroChoosingGUI.getStage().close();
                    }
                });
                game.setVisible(true);
            } else if (o instanceof HeroPickedRequest) {
                HeroPickedRequest request = (HeroPickedRequest) o;
                heroChoosingGUI.setAllyIcon(request.getHeroSlot(), facade.getLibraryHero(request.getHeroIndex()).getIcon());
                heroChoosingGUI.announceSelectedHero(request.getPlayerName(),
                        facade.getLibraryHero(request.getHeroIndex()).getName());
            } else if (o instanceof CountDownRequest) {
                CountDownRequest request = (CountDownRequest) o;
                heroChoosingGUI.setCountDownTime(request.getCount());
            } else if (o instanceof HeroMoveRequest) {
                HeroMoveRequest request = (HeroMoveRequest) o;
                game.getGameMap().handleHeroMoveRequest(request);
            } else if (o instanceof HeroAttackRequest) {
                HeroAttackRequest request = (HeroAttackRequest) o;
                game.getGameMap().handleHeroAttackRequest(request);
            } else if (o instanceof HeroChoosingRequest) {
                final HeroChoosingRequest m = (HeroChoosingRequest) o;
                facade.setGameIndex(m.getGameIndex());
                facade.setHeroSlot(m.getHeroSlot());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        mainMenuGUI.getStage().close();
                        Stage stage = new Stage();
                        try {
                            heroChoosingGUI.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                });

            } else if (o instanceof TurnControlRequest) {
                TurnControlRequest request = (TurnControlRequest) o;
                if (facade.getHeroSlot() == request.getTurnIndex()) {
                    if (!facade.getMainHero().getStatus().equals(HeroStatus.dead))
                        facade.setIsLocked(false);
                    else {
                        if (facade.getMainHero().getRespawnTurnNum() == 0) {
                            com.write(new HeroRespawnRequest(facade.getGameIndex(),facade.getHeroSlot()));
                            facade.setIsLocked(false);
                        } else {
                            facade.getMainHero().decreaseRespawnNum();
                            com.write(new TurnControlRequest(facade.getHeroSlot(),facade.getGameIndex()));
                        }
                    }
                }
                facade.resetAP(request.getHeroSlot());
                facade.setTurnIndex(request.getTurnIndex());
                game.getTurnPanel().setStatusMessage(request.getTurnIndex());
                game.getControlPanel().repaint();

            } else if(o instanceof HeroRespawnRequest){
                HeroRespawnRequest request=(HeroRespawnRequest) o;
                game.getGameMap().handleHeroRespawnRequest(request);
            }
            //invalid account
            else if (o instanceof Status) {
                Status s = (Status) o;
                if (s.equals(Status.fail)) {
                    login.setActionText("Invalid username or password!");
                }
                if (s.equals(Status.already)) {
                    login.setActionText("Account already in use!");
                }
            }
            o = com.read();
        }
    }
}
