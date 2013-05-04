package Controllers.Server.GameManager.Comparators;

import Controllers.Server.GameManager.Player;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/3/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getScore()- o1.getScore();
    }
}
