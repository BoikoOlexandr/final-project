package Game.Core;

import Game.view.Printer;

import java.sql.SQLException;

public class DefaultAct extends Act{

    private int id;
    public String header;
    public String text;
    public String choises;

    public DefaultAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }

    public void print_act(){
        Printer.print(header);
        Printer.print(Printer.format_text(text));
    }
}
