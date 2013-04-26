package Controllers.Server;

import Controllers.Server.Mappers.AccountMapper;
import Controllers.Server.Mappers.CellMapper;
import View.Ingame.Cell;
import model.AccountSystem.Account;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/31/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //get all the accounts from the database
    @Override
    public List<Account> allAccount() {
        List<Account> messages = jdbcTemplate.query("select * from accounts", new AccountMapper());
        return messages;
    }

    //get specified account from the database
    @Override
    public Account getAccount(Account account) {

        try {
            Account t = jdbcTemplate.queryForObject("select * from accounts where username=(?) and password=(?) limit 1"
                    , new AccountMapper(), account.getUsername(), account.getPassword());
            return t;
        } catch (Exception ex) {
            System.out.println("not found!!!");
            return null;
        }
    }

    // add new account to the database
    @Override
    public void addAccount(Account account) {
        jdbcTemplate.update("insert into accounts(username,password) values(?,?)"
                , account.getUsername(), account.getPassword());
    }

    public Cell getHeroBeginPosition(int index) {
        try {
            Cell c = jdbcTemplate.queryForObject("select * from heroBeginPositions where id=(?)", new CellMapper()
                    , index);
            return c;
        } catch (Exception e) {
            return null;
        }
    }
}
