package Game.Core;

import Game.ORM.DbConnection;
import Game.ORM.Table;

import java.sql.SQLException;

public abstract class Act extends Table {


    public Act(int id, String day) throws SQLException, IllegalAccessException {
        get_row(DbConnection.get_instance().get_row_by_id(id, day));
    }

    public abstract void print_act();


}
