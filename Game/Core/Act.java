package Game.Core;

import Game.ORM.DbConnection;
import Game.ORM.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Act extends Table {
    private int id;
    public String header;
    public String text;
    public String choises;

    public Act(ResultSet data) throws SQLException, IllegalAccessException {
        get_row(data);
    }

    public Act(int id, String day) throws SQLException, IllegalAccessException {
        get_row(DbConnection.get_instance().get_row_by_id(id, day));
    }

    public String parse_text() {
        Pattern pattern = Pattern.compile("[$].+[$]");
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.find());
        System.out.println(matcher.group());
        return text;
    }
}
