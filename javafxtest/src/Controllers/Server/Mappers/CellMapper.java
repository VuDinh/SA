package Controllers.Server.Mappers;

import View.Ingame.Cell;
import model.AccountSystem.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/15/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellMapper implements RowMapper<Cell> {
    public  CellMapper(){}

    //mapping the row of the database to one Account object
    @Override
    public Cell mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cell c=new Cell(rs.getInt(2),rs.getInt(3));
        return c;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
