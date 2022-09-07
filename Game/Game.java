package Game;

import Game.Core.Day;
import Game.ORM.DbConnection;
import Game.view.Printer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private final List<Day> days= new ArrayList<>();

    private final Logger logger;
    public Game () throws SQLException {
        DbConnection.get_instance(settings.URL, settings.Table);
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
        this.init_days();
    }
    public void init_days() throws SQLException {
        for (String name: DbConnection.get_instance().get_table_names()){
            if(name.startsWith("Day")){
                try {
                    int day_number = Integer.parseInt(name.substring(3));
                    days.add(new Day(day_number));
                }catch (NumberFormatException e){
                    logger.log(Level.WARNING, String.format("Can't init %s. Check database", name));
                }
            }
        }
    }
    public void Start(){
        for(Day day : days){
            Printer.print_day_header(day);

        }
    }
}
