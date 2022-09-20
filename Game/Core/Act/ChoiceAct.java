package Game.Core.Act;

import Game.Core.Day;
import Game.view.Printer;

public class ChoiceAct extends Act{


    private int __number_of_choice;

    public int get_choice_count() {
        return __number_of_choice;
    }

    public ChoiceAct(int id, Day day) throws Exception {
        super(id, day);
        this.__input_type = "choice";
        prepare_choice();
    }
    @Override
    public void print_act() throws Exception {
        Printer.print("");
        Printer.print(this.header);
        Printer.print_splitter();
        Printer.print_formatted_text(this.text);
        Printer.print_splitter();
        Printer.print(this.choice);
        Printer.print("");
        __input.input();
    }

    private void prepare_choice(){
            StringBuilder prepared_choice = new StringBuilder();
            String[] lines = choice.split("\n");
            for(int i = 0; i < lines.length; i ++){
                lines[i] = String.format("%d) %s\n", i+1, lines[i]);
                prepared_choice.append(lines[i]);
                __number_of_choice = i+1;
            }
            choice = prepared_choice.toString();
    }

}
