package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.PlayingGameRequest;
import Controllers.Server.AccountDao;

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
}
