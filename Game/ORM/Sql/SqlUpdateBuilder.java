package Game.ORM.Sql;

public class SqlUpdateBuilder extends SqlBuilder {
    public SqlUpdateBuilder(String table){
        SQL = "UPDATE " + table + " ";
    }
}
