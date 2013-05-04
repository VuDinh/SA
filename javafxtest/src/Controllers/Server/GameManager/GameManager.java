package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.HeroAttackRequest;
import Controllers.Requests.HeroMoveRequest;
import Controllers.Requests.PlayingGameRequest;
import Controllers.Requests.TurnControlRequest;
import Controllers.Server.AccountDao;
import Utilities.Utilizer;
import model.MessageSystem.Message;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/11/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameManager {
    ArrayList<GameMatch> gameMatches;
    private int gameNum;
    AccountDao dao;
    public GameManager(){
        gameMatches =new ArrayList<GameMatch>();
        gameNum=0;
        Utilizer.initializeContent();
    }
    public void addPlayer(Communicator com){
        if(gameNum==0){
            gameNum++;
            GameMatch gameMatch =new GameMatch(0);
            gameMatch.setDao(dao);
            gameMatch.addPlayer(com);
            gameMatches.add(gameMatch);
        }
        else{
            GameMatch gameMatch = gameMatches.get(gameNum-1);
            if(!gameMatch.isFull()){
                gameMatch.addPlayer(com);
            }
            else{
                GameMatch newGameMatch =new GameMatch(gameNum);
                newGameMatch.addPlayer(com);
                gameMatches.add(newGameMatch);
                gameNum++;
            }
        }
    }
    public void chooseHero(Communicator com, PlayingGameRequest request){
        gameMatches.get(request.getMatchIndex()).chooseHero(com, request.getHeroSlot(), request.getHeroIndex());
    }
    public void setDao(AccountDao dao){
        this.dao=dao;
    }
    public void sendRequestBack(){

    }
    public void removePlayer(Communicator com){

    }
    public void handleHeroMoveRequest(HeroMoveRequest request){
        gameMatches.get(request.getGameIndex()).handleHeroMoveRequest(request);
    }
    public void handleHeroAttackRequest(HeroAttackRequest request){
        gameMatches.get(request.getGameIndex()).handleHeroAttackRequest(request);
    }
    public void sendMessageToTeam(Message mes){
        gameMatches.get(mes.getGameIndex()).sendMessageToTeam(mes);
    }
    public void sendMessageToAll(Message mes){
        gameMatches.get(mes.getGameIndex()).sendMessageToAll(mes);
    }
    public void sendMessageToPlayer(Message mes){
        gameMatches.get(mes.getGameIndex()).sendMessageToPlayer(mes);
    }
    public void nextTurn(TurnControlRequest request){
        gameMatches.get(request.getGameIndex()).nextTurn();
    }
}
