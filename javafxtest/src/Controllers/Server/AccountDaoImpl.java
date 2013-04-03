package Controllers.Server;

import model.AccountSystem.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public List<Account> allAccount() {
        List<Account> messages = jdbcTemplate.query("select * from accounts", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account acc=new Account(rs.getString(1),rs.getString(2),0);
                return acc;
            }
        });
        return messages;
    }

    @Override
    public Account getAccount(Account account) {
        List<Account> lst=allAccount();
        for(Iterator it=lst.iterator();it.hasNext();){
            Account temp=(Account)it.next();
            if(temp.getUsername().equals(account.getUsername())&&(temp.getPassword().equals(account.getPassword()))){
                return temp;
            }
        }
        return null;
    }
    @Override
    public void addAccount(Account account) {
        jdbcTemplate.update("insert into accounts(username,password) values(?,?)", account.getUsername(),account.getPassword());
    }
}
