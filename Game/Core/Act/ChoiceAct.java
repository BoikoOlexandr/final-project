package Game.Core.Act;

import Game.view.Printer;

public class ChoiceAct extends Act{


    public ChoiceAct(int id, String day) throws Exception {
        super(id, day);
        this.__input_type = "choise";
    }
    @Override
    public void print_act() throws Exception {
        Printer.print("");
        Printer.print(this.header);
        Printer.print_splitter();
        Printer.print_formated_text(this.text);
        Printer.print_splitter();
        Printer.print(this.choises);
        Printer.print("");
        __input.input();
    }

}
