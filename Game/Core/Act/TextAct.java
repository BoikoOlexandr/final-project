package Game.Core.Act;

import Game.Core.Day;
import Game.view.Printer;

public class TextAct extends Act{

    public TextAct(int id, Day day) throws Exception {
        super(id, day);
        this.__input_type = "default";
    }


    @Override
    public void print_act() throws Exception {
        Printer.print_formatted_text(this.text);
        __input.input();
    }
}
