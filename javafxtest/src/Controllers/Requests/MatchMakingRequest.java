package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/10/13
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchMakingRequest implements Serializable {
    int playerNum;
    public MatchMakingRequest(){
        playerNum=0;
    }
    public MatchMakingRequest(int playerNum){
        this.playerNum=playerNum;
    }
    public int getPlayerNum(){
        return playerNum;
    }
}
