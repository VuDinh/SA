package Controllers.Server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/31/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class DbUtilities {
    private DataSource dataSource;

    private Log log = LogFactory.getLog(DbUtilities.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createDb() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Resource script = new ClassPathResource("Controllers/Server/accounts.sql");
        JdbcTestUtils.executeSqlScript(jdbcTemplate, script, true);
        /*try {
            log.info("before query");
            String msg = jdbcTemplate.queryForObject("select username from accounts limit 1", String.class);
            log.info("schema found: "+msg);
            log.info("after query");
        } catch(Exception e) {
            //no db present, so create the tables
            log.info("No tables found, creating db");
            Resource script = new ClassPathResource("Controllers/Server/accounts.sql");
            JdbcTestUtils.executeSqlScript(jdbcTemplate, script, true);
        }*/
    }
}
