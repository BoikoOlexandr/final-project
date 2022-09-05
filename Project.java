import Core.Act;
import Core.Day;
import Core.ORM.DbConnection;
import Core.Player;
import Core.view.Printer;
import Core.settings;

import java.io.IOException;
import java.sql.SQLException;

public class Project {
    public static void main(String[] args) throws SQLException, IllegalAccessException, IOException {
        DbConnection connection = DbConnection.get_instance(settings.URL, settings.Table);

        Day day1 = new Day(0);
        day1.add_act(1);
        day1.add_act(2);
        day1.add_act(3);
        Printer.print(day1);

    }
}
