package Game.Core.Act;


import Game.view.Printer;

import java.sql.SQLException;

public class HeaderAct extends Act {

    public HeaderAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }

    @Override
    public void print_act() throws Exception {
        Printer.print(this.header);
        Printer.print_splitter();
        Printer.print_formated_text(this.text);
        Printer.print(
                get_prompt("default")
        );
    }
}
