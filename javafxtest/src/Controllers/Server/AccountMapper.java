package Controllers.Server;

import model.AccountSystem.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/4/13
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountMapper implements RowMapper<Account> {
    public  AccountMapper(){

    }
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account acc=new Account(rs.getString(1),rs.getString(2),0);
        return acc;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
