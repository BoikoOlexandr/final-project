package Game.Core.Act;

import Game.Core.Day;
import Game.view.Printer;

public class ChosenAct extends Act{
    private final int __offset;
    public ChosenAct(int id, Day day, int offset) throws Exception {
        super(id, day);
        this.__offset = offset;
    }

    @Override
    public void print_act() throws Exception {
        Printer.print_formatted_text(this.text);
        __input.input(__offset);
    }
}
