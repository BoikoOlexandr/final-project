package Game.Command;

import Game.Core.Day;

public class QuitCommand implements Command {
    @Override
    public void execute(String command, Day day) {
        System.exit(0);
    }
}
