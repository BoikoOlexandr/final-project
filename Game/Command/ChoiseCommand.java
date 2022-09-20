package Game.Command;

import Game.Core.Day;

public class ChoiseCommand implements Command {
    @Override
    public void execute(String command, Day day) throws Exception {
        System.out.println(command);
        String[] commandes = command.split(" ");
        int choise = Integer.parseInt(commandes[0]) + day.getAct_id();
        int choises_count = Integer.parseInt(commandes[1]) + day.getAct_id();
        int offset = choises_count - choise + 1;
        day.setAct_id(choise);
        day.setOffset(offset);
    }
}
