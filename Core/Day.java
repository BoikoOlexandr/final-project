package Core;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Day {
    List<Act> act_list = new ArrayList<>();
    private final int day_number;
    public Day(int day_number){
        this.day_number = day_number;
    }
    public List<Act> getAct_list() {
        return act_list;
    }
    public void add_act  (Act act) {
        this.act_list.add(act);
    }

    public void add_act (int act_id) throws SQLException, UnsupportedEncodingException, IllegalAccessException {
        this.add_act(new Act(act_id, day_number));
    }
}
