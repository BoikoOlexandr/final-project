package Game.Core;

import Game.Core.Act.Act;
import Game.Core.Act.ActGenerator;
import Game.ORM.DbConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Day {
    List<Act> act_list = new ArrayList<>();
    private final String day_name;

    public int getDay_number() {
        return day_number;
    }

    private final int day_number;

    public Day(int day_number) throws SQLException, IllegalAccessException {
        this.day_number = day_number;
        this.day_name = String.format("day%d", day_number);
        this.init_day();
    }

    private void init_day() throws SQLException, IllegalAccessException {
        DbConnection.get_instance().setTABLE(day_name);
        int number_of_acts = DbConnection.get_instance().get_number_of_rows();
        for (int id = 1; id<= number_of_acts; id++){
            this.add_act("default", id);
        }
    }
    public List<Act> getAct_list() {
        return act_list;
    }

    public void add_act(String type, int id) throws SQLException, IllegalAccessException {
        this.act_list.add(new ActGenerator().get_act(id, day_name));
    }


    public void print_day() {
        for (Act act: act_list){
            act.print_act();
        }
    }
}
