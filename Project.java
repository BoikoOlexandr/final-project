import Game.Game;
import Game.ORM.DbConnection;
import Game.settings;

import java.io.IOException;
import java.sql.SQLException;

public class Project {
    public static void main(String[] args) throws SQLException {
        new Game().Start();
    }
}
