package Game.Command;

import Game.Core.Day;

public interface Command {
    void execute(String command, Day day) throws Exception;
}
