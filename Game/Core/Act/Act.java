package Game.Core.Act;

import Game.ORM.DbConnection;
import Game.ORM.Table;

import java.sql.SQLException;

public abstract class Act extends Table {
    public  int id;
    public String header;
    public String text;
    public String choises;

    public Act(int id, String day) throws SQLException, IllegalAccessException {
        get_row(DbConnection.get_instance().get_row_by_id(id, day));
    }

    public abstract void print_act() throws Exception;

    protected String get_prompt(String value) throws Exception {
        return OutputPromptList.get_instance().get_prompts().get(value);
    }


}
