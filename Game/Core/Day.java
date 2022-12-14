package Game.Core;

import Game.Core.Act.ActGenerator;
import Game.ORM.DbConnection;
import Game.view.Printer;

public class Day {
    private final String day_name;

    private final int day_number;
    private int offset = -1;

    private int act_id = 1;

    private final int number_of_acts;

    public Day(int day_number) throws Exception {
        this.day_number = day_number;
        this.day_name = String.format("day%d", day_number);
        DbConnection.get_instance().set_table(day_name);
        this.number_of_acts = DbConnection.get_instance().get_number_of_rows();
    }

    public void print_act(int id) throws Exception {
        DbConnection.get_instance().set_table(day_name);
        if (offset == -1) {
            new ActGenerator().get_act(id, this).print_act();
        } else {
            int offset = this.offset;
            this.offset = -1;
            new ActGenerator().get_chosen_act(id, this, offset).print_act();
        }
    }

    public void print_header() {
        Printer.print(String.format("ДЕНЬ %d",day_number));
    }
    public void run_day() throws Exception {
        this.print_header();
        while (act_id <= number_of_acts) {
            print_act(act_id);
        }
    }

    public int get_act_id() {
        return act_id;
    }

    public void set_act_id(int act_id) {
        this.act_id = act_id;
    }

    public void set_offset(int offset) {
        this.offset = offset;
    }

    public int get_day_number() {
        return day_number;
    }

    public String get_day_name() {
        return day_name;
    }
}
