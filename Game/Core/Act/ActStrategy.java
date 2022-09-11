package Game.Core.Act;

public class ActStrategy {
    public Act get_act(String act_name, int id, String day) throws Exception {
        Act act = null;
        switch (act_name){
            case "default": act = new DefaultAct(id, day);
        }
        return act;
    }
}
