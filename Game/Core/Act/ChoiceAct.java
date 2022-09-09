package Game.Core.Act;

import Game.view.Printer;

import java.sql.SQLException;

public class ChoiceAct extends Act{

    public ChoiceAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }
    @Override
    public void print_act() {
        Printer.print("");
        Printer.print(this.header);
        Printer.print_splitter();
        Printer.print(Printer.format_text(this.text));
        Printer.print_splitter();
        Printer.print(this.choises);
    }
}
