package Game.Core.Act;

import java.sql.SQLException;

public class DefaultAct extends Act{
    public DefaultAct(int id, String day) throws SQLException, IllegalAccessException {
        super(id, day);
    }

    @Override
    public void print_act() {

    }
}
