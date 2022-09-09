package Game.Core.Act;

import java.sql.SQLException;

public class ActGenerator {
    public Act get_act(int id, String day) throws SQLException, IllegalAccessException {
        Act act = null;
        DefaultAct default_act = new DefaultAct(id, day);
        if(default_act.header == null || default_act.header.equals("")){
            act = new TextAct(id, day);
        } else if (default_act.choises == null || default_act.choises.equals("")) {
            act = new HeaderAct(id, day);
        }else {
            act = new ChoiceAct(id, day);
        }
        return act;
    }
}
