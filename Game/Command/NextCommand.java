package Game.Command;

import Game.Core.Day;

public class NextCommand implements Command{
    @Override
    public void execute(String command, Day day) throws Exception {

    }

    public void execute(int offset, Day day) throws Exception {
        int act_id = day.getAct_id() + offset;
        day.setAct_id(act_id);
    }

}
