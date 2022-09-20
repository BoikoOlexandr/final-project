package Game.Core.Act;

import Game.Core.Day;

public class ActGenerator {
    public Act get_act(int id, Day day) throws Exception {
        Act act;
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

    public Act get_chosen_act(int id, Day day, int offset) throws Exception {
        return new ChosenAct(id, day, offset);
    }
}
