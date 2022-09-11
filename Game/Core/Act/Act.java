package Game.Core.Act;

import Game.ORM.DbConnection;
import Game.ORM.Table;
import Game.view.Input;

public abstract class Act extends Table {
    public  int id;
    public String header;
    public String text;
    public String choises;
    protected Input __input = new Input(this);

    public String get__input_type() {
        return __input_type;
    }

    protected String __input_type;

    public Act(int id, String day) throws Exception {
        get_row(DbConnection.get_instance().get_row_by_id(id, day));
    }

    public abstract void print_act() throws Exception;

    public String get_prompt() throws Exception {
        return OutputPromptList.get_instance().get_prompts().get(this.__input_type);
    }


}
