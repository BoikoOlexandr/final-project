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
        Day zero = new Day(0);
        zero.add_act(1);
        zero.add_act(2);
        zero.add_act(3);
        Printer.print(zero);
        Day day1 = new Day(1);
        day1.add_act(1);
        day1.add_act(2);
        day1.add_act(3);
        day1.add_act(4);
        day1.add_act(5);
        day1.add_act(6);
        day1.add_act(7);
        day1.add_act(8);
        day1.add_act(9);
        Printer.print(day1);
    }
}
