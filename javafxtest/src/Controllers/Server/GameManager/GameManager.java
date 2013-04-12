package Controllers.Server.GameManager;

import Controllers.Communicator;

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
    public GameManager(){
        games=new ArrayList<Game>();
        gameNum=0;
    }
    public void addPlayer(Communicator com){
        if(gameNum==0){
            gameNum++;
            Game game=new Game();
            game.addPlayer(com);
            games.add(game);
        }
        else{
            Game game=games.get(gameNum-1);
            if(!game.isFull()){
                game.addPlayer(com);
            }
            else{
                Game newGame=new Game();
                newGame.addPlayer(com);
                games.add(newGame);
                gameNum++;
            }
        }
    }
    public void sendRequestBack(){

    }
    public void removePlayer(Communicator com){

    }
}
