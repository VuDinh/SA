package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/10/13
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchRequest implements Serializable {
    int playerNum;
    public MatchRequest(){
        playerNum=0;
    }
    public MatchRequest(int playerNum){
        this.playerNum=playerNum;
    }
    public int getPlayerNum(){
        return playerNum;
    }
}
