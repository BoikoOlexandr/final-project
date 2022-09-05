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
        get_row(data);
    }
    public Act(int id, String day) throws SQLException, IllegalAccessException, UnsupportedEncodingException {
        get_row(DbConnection.get_instance().get_row_by_id(id, day));
    }
}
