package Game.Command;

import Game.Core.Day;

public class NextCommand implements Command{
    @Override
    public void execute(String command, Day day){

    }

    public void execute(int offset, Day day){
        int act_id = day.get_act_id() + offset;
        day.set_act_id(act_id);
    }

}
