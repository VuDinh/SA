package Controllers.Server;

import View.Ingame.Cell;
import model.AccountSystem.Account;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/31/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountDao {
    List<Account> allAccount();

    Account getAccount(Account account);
    void addAccount(Account account);
    public Cell getHeroBeginPosition(int index);
}
