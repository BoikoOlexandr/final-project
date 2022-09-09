package Game.Core;

import java.sql.SQLException;

public class ActStrategy {
    public Act get_act(String act_name, int id, String day) throws SQLException, IllegalAccessException {
        Act act = null;
        switch (act_name){
            case "default": act = new DefaultAct(id, day);
        }
        return act;
    }
}
