package Chat;

import model.AccountSystem.Account;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/20/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountList implements Serializable {
    public ArrayList<Account> list;

    public AccountList() {
        list=new ArrayList<Account>();

    }

    public ArrayList<Account> getList() {
        return list;
    }

    public void setList(ArrayList<Account> list) {
        this.list = list;
    }
    public void addAccount(Account account){
        list.add(account);
    }

}
