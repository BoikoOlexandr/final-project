package Game.Core.Act;

import Game.view.Printer;

public class TextAct extends Act{

    public TextAct(int id, String day) throws Exception {
        super(id, day);
        this.__input_type = "default";
    }


    @Override
    public void print_act() throws Exception {
        Printer.print_formated_text(this.text);
        __input.input();
    }
}
