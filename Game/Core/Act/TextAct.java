package Game.Core.Act;

import Game.view.Printer;

import java.sql.SQLException;

public class TextAct extends Act{

    public TextAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }


    @Override
    public void print_act() {
        Printer.print(Printer.format_text(this.text));
    }
}
