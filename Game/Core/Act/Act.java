package Game.Core.Act;

import Game.Core.Day;
import Game.ORM.DbConnection;
import Game.ORM.Table;
import Game.view.Input;

public abstract class Act extends Table {
    public  int id;
    public String header;
    public String text;
    public String choises;
    protected Input __input;

    public String get__input_type() {
        return __input_type;
    }

    protected String __input_type;

    public Act(int id, Day day) throws Exception {
        this.__input = new Input(this, day);
        get_row(DbConnection.get_instance().get_row_by_id(id, day.getDay_name()));
    }

    public abstract void print_act() throws Exception;

    public String get_prompt() throws Exception {
        return OutputPromptList.get_instance().get_prompts().get(this.__input_type);
    }


    public int get_choise_count() {
        return 0;
    }
}
