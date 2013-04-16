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
    ArrayList<Game> games;
    private int gameNum;
    AccountDao dao;
    public GameManager(){
        games=new ArrayList<Game>();
        gameNum=0;
    }
    public void addPlayer(Communicator com){
        if(gameNum==0){
            gameNum++;
            Game game=new Game(0);
            game.setDao(dao);
            game.addPlayer(com);
            games.add(game);
        }
        else{
            Game game=games.get(gameNum-1);
            if(!game.isFull()){
                game.addPlayer(com);
            }
            else{
                Game newGame=new Game(gameNum);
                newGame.addPlayer(com);
                games.add(newGame);
                gameNum++;
            }
        }
    }
    public void startPlayingGame(Communicator com,PlayingGameRequest request){
        games.get(request.getMatchIndex()).startPlayingGame(com,request.getHeroSlot(),request.getHeroIndex());
    }
    public void setDao(AccountDao dao){
        this.dao=dao;
    }
    public void sendRequestBack(){

    }
    public void removePlayer(Communicator com){

    }
}
