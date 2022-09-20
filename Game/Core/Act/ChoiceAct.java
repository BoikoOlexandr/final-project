package Game.Core.Act;

import Game.Core.Day;
import Game.view.Printer;

public class ChoiceAct extends Act{


    private int __number_of_choises;

    public int get_choise_count() {
        return __number_of_choises;
    }

    public ChoiceAct(int id, Day day) throws Exception {
        super(id, day);
        this.__input_type = "choise";
        prepare_choise();
    }
    @Override
    public void print_act() throws Exception {
        Printer.print("");
        Printer.print(this.header);
        Printer.print_splitter();
        Printer.print_formatted_text(this.text);
        Printer.print_splitter();
        Printer.print(this.choises);
        Printer.print("");
        __input.input();
    }

    private void prepare_choise(){
            StringBuilder prepared_choise = new StringBuilder();
            String[] lines = choises.split("\n");
            for(int i = 0; i < lines.length; i ++){
                lines[i] = String.format("%d) %s\n", i+1, lines[i]);
                prepared_choise.append(lines[i]);
                __number_of_choises = i+1;
            }
            choises = prepared_choise.toString();
    }

}
