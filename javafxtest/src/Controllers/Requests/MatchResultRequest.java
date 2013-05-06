package Controllers.Requests;

import Controllers.Server.GameManager.Team;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 5/6/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchResultRequest implements Serializable {
    private Team winTeam;
    private int gameIndex;
    public MatchResultRequest(Team winTeam,int gameIndex){
        this.winTeam=winTeam;
    }
    public Team getWinTeam(){
        return winTeam;
    }
    public int getGameIndex(){
        return gameIndex;
    }

}
