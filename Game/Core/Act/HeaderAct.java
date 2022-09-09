package Game.Core.Act;


import Game.view.Printer;

import java.sql.SQLException;

public class HeaderAct extends Act {

    public HeaderAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }

    @Override
    public void print_act() {
        Printer.print(this.header);
        Printer.print(Printer.format_text(this.text));
    }
}
