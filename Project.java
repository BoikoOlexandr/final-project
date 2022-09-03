import Core.Act;
import Core.ORM.DbConnection;
import Core.Printer;
import Core.settings;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Project {
    public static void main(String[] args) throws SQLException, IllegalAccessException, UnsupportedEncodingException {
        DbConnection connection = DbConnection.get_instance(settings.URL, settings.Table);
        Act act = new Act(1);
        Printer.print(act);
    }
}
