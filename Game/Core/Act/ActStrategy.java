package Game.Core.Act;

import Game.Core.Day;

public class ActStrategy {
    public Act get_act(String act_name, int id, Day day) throws Exception {
        Act act = null;
        switch (act_name){
            case "default": act = new DefaultAct(id, day);
        }
        return act;
    }
}
