package Core;

import Core.ORM.DbConnection;
import Core.ORM.Table;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Act extends Table {
    private int id;
    public String header;
    public String text;
    public String choises;

    public Act(ResultSet data) throws SQLException, IllegalAccessException, UnsupportedEncodingException {
        super(data);
    }
    public Act(int id, int day) throws SQLException, IllegalAccessException, UnsupportedEncodingException {
        super(DbConnection.get_instance().get_row_by_id(id, String.format("day%d",day)));
    }
}
