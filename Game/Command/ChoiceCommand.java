package Game.Command;

import Game.Core.Day;

public class ChoiceCommand implements Command {
    @Override
    public void execute(String command, Day day) {
        String[] commands = command.split(" ");
        int choice = Integer.parseInt(commands[0]) + day.get_act_id();
        int choice_count = Integer.parseInt(commands[1]) + day.get_act_id();
        int offset = choice_count - choice + 1;
        day.set_act_id(choice);
        day.set_offset(offset);
    }
}
