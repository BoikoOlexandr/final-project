package Game;

import Game.Core.Day;
import Game.Core.DaySort;
import Game.ORM.DbConnection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Game {
    private final List<Day> days= new ArrayList<>();

    private static Game instance;

    private int current_day_id=0;

    private int current_act_in_day = 1;

    public static Game get_instance() throws Exception {
        if(instance==null){
            instance = new Game();
        }
        return instance;
    }
    private final Logger logger;
    private Game () throws Exception {
        DbConnection.get_instance(settings.URL, settings.Table);
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
        this.init_days();
    }
    private void init_days() throws Exception{
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
        days.sort(new DaySort());
    }

    public void Start() throws Exception {
        for(Day day: days){
            day.run_day();
        }
    }
    public void get_first_act() throws Exception {
        days.get(0).print_header();
        days.get(0).print_act(current_act_in_day);
    }







}

