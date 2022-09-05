import Core.Act;
import Core.Day;
import Core.ORM.DbConnection;
import Core.Printer;
import Core.settings;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Project {
    public static void main(String[] args) throws SQLException, IllegalAccessException, IOException {
        DbConnection connection = DbConnection.get_instance(settings.URL, settings.Table);

        Printer.print(new Act(9, "day1"));
    }
}
