package Controllers.Client;

import Controllers.Communicator;
import Controllers.Requests.*;
import Controllers.Server.GameManager.GameMatch;
import Controllers.Server.GameManager.Player;
import Utilities.Utilizer;
import View.HeroChoosing.HeroChoosingGUI;
import View.Ingame.Cell;
import View.Ingame.ChatPanel;
import View.Ingame.Game;
import View.Login.LoginFrame;
import View.MainMenu.MainMenuGUI;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.AccountSystem.Account;
import model.Animations.HeroAnimation;
import model.Facade.Facade;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroStatus;
import model.HeroSystem.Teleport;
import model.MessageSystem.Message;
import model.AccountSystem.Status;
import model.MessageSystem.MessageStatus;
import model.MonsterSystem.Monster;
import model.MonsterSystem.Tower;
import model.Skills.SkillStatus;

import java.util.ArrayList;
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

                /*Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //when someone enters the game
                        if (!temp.getStatus().equals(Status.quit)) {
                            chatPanel.addUser(temp.getUsername());
                            chatPanel.addStatusMessage(temp.getUsername() + " has joined the game!!!");
                        }
                        //when someone leaves the game

                    }
                });*/
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
                        for(int i=0;i<Utilizer.MAXPLAYER;i++)
                        {
                            String playerName=facade.getGame().getPlayer(i).getPlayerName();
                            chatPanel.addUser(playerName);
                            chatPanel.addStatusMessage(playerName + " has joined the game!!!");
                        }
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
                handleHeroMoveRequest(request);
            } else if (o instanceof HeroAttackRequest) {
                HeroAttackRequest request = (HeroAttackRequest) o;
                handleHeroAttackRequest(request);
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
                handleHeroRespawnRequest(request);
            } else if(o instanceof MatchResultRequest){
                MatchResultRequest request=(MatchResultRequest) o;
                game.getTurnPanel().setResultStatus(request.getWinTeam());
                facade.setIsLocked(true);
            } else if(o instanceof QuitRequest){
                final QuitRequest request=(QuitRequest) o;
                if(request.getHeroSlot()==facade.getHeroSlot()){
                    com.close();
                    game.dispose();
                    System.exit(0);
                }
                else{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            chatPanel.removeUser(facade.getGame().getPlayer(request.getHeroSlot()).getPlayerName());
                            chatPanel.addStatusMessage(facade.getGame().getPlayer(request.getHeroSlot()).getPlayerName()
                                    + " has left the game!!!");
                        }
                    });

                }
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

    public void handleHeroMoveRequest(HeroMoveRequest request) {
        Hero temp = facade.getHeroBySlotIndex(request.getSlotIndex());
        Utilizer.MOVEMAP[temp.getRow()][temp.getCol()] = 0;
        temp.setShortestPathSelect(request.getHero().getShortestPathSelect());
        HeroAnimation.move(temp, game);
    }

    public void handleHeroAttackRequest(HeroAttackRequest request) {
        Hero temp = facade.getHeroBySlotIndex(request.getSlotIndex());
        Player attackingPlayer = facade.getMatch().getPlayer(request.getSlotIndex());
        temp.setIsChosen(true);
        temp.setStatus(HeroStatus.attacking);
        temp.setCurrentSkill(request.getHero().getCurrentSkillIndex());
        temp.getCurrentSkill().setPath(request.getPath());
        temp.getCurrentSkill().setDamageCell(request.getDmgCell());
        temp.getCurrentSkill().setStatus(SkillStatus.after);
        temp.setCurrentSprite(request.getHero().getCurrentSpriteIndex());
        game.getGameMap().setSelectedCell(request.getSelectedCell());
        HeroAnimation.attack(temp, game.getGameMap());
        //look for affected character
        for (Iterator it = request.getDmgCell().iterator(); it.hasNext(); ) {
            Cell cell = (Cell) it.next();
            Player player = facade.getPlayerByCord(cell.getRowPos(), cell.getColPos());
            if (player != null) {
                if (!player.getTeam().equals(attackingPlayer.getTeam())) {
                    Hero attackedHero = player.getHero();
                    attackedHero.setHP(attackedHero.getHP() - temp.getCurrentSkill().getDamage(temp));
                    if (attackedHero.getHP() <= 0 && !attackedHero.getStatus().equals(HeroStatus.dead)) {
                        //set dead status
                        attackedHero.setHP(0);
                        attackedHero.setStatus(HeroStatus.dead);
                        attackedHero.setRespawnTurnNum(1);
                    }
                }
            }
            Monster attackedMonster = facade.getMonsterByCord(cell.getRowPos(), cell.getColPos());
            if (attackedMonster != null) {
                attackedMonster.setHP(attackedMonster.getHP() - temp.getCurrentSkill().getDamage(temp));
                if (attackedMonster.getHP() <= 0) {
                    //set dead status
                    attackedMonster.setHP(0);
                    attackedMonster.setImageIndex(11);

                }
            }
        }
        for(Iterator it = facade.getMatch().getTower().iterator(); it.hasNext();){
            Tower attackedTower = (Tower)it.next();
            System.out.println("out if"+attackedTower.getTeam()+":"+attackingPlayer.getTeam());
            if(attackedTower.getTeam()!=attackingPlayer.getTeam() && attackedTower.isHit(request.getDmgCell())==true){
                System.out.println("in if"+attackedTower.getTeam()+":"+attackingPlayer.getTeam());
                attackedTower.setHP(attackedTower.getHP() - temp.getCurrentSkill().getDamage(temp));
                if (attackedTower.getHP() <= 0) {
                    //set dead status
                    attackedTower.setHP(0);
                    attackedTower.setImageIndex(13);
                    attackedTower.setBroken(true);
                    facade.getMatch().resetSightTeam1();
                    facade.getMatch().resetSightTeam2();
                    if(attackedTower.isMain()){
                        com.write(new MatchResultRequest(attackingPlayer.getTeam(),facade.getGameIndex()));
                    }
                }
                break;
            }
        }
        game.getGameMap().repaint();
    }

    public void tele(){
        ArrayList<Teleport> teleport =facade.getMatch().getTeleport();

    }

    public void handleHeroRespawnRequest(HeroRespawnRequest request) {
        Hero hero=facade.getHeroBySlotIndex(request.getHeroSlot());
        hero.setStatus(HeroStatus.standing);
        hero.resetHP();
        hero.resetAP();
        Utilizer.MOVEMAP[hero.getRow()][hero.getCol()]=0;
        hero.setRow(request.getRespawnPos().getRowPos());
        hero.setCol(request.getRespawnPos().getColPos());
        hero.setX(request.getRespawnPos().getX());
        hero.setY(request.getRespawnPos().getY());
        Utilizer.MOVEMAP[hero.getRow()][hero.getCol()]=12;
        game.getGameMap().repaint();
    }
}
