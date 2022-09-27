package Game.ORM.Sql;

public class SqlSelectBuilder extends SqlBuilder {
    public SqlSelectBuilder(){
        SQL = "SELECT * ";
    }
    public SqlSelectBuilder(String field){
        SQL = "SELECT " + field + " ";
    }

}
