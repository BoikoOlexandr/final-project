package Game.ORM.Sql;

import java.util.Map;

public abstract class SqlBuilder {
    protected String SQL;
    protected String set_statement;
    protected String where_statement;
    protected String from_statement;

    public SqlBuilder from(String table_name){
        from_statement = " FROM " + table_name + " ";
        return this;
    }
    public SqlBuilder where(String field){
        where_statement = " WHERE " + field + "=? ";
        return this;
    }
    public SqlBuilder where(String field, String value){
        where_statement = " WHERE " + field + "=\"" + value +"\" ";
        return this;
    }

    public SqlBuilder where(Map<String, String> map_field_value){
        SQL += " WHERE ";
        set_WHERE_statement(map_field_value);
        return this;
    }
    public SqlBuilder set(Map<String, String> field_value_map){
        set_statement = " SET ";
        set_SET_statement(field_value_map);
        return this;
    }

    public String get_SQL() {
        if(from_statement != null){
            SQL+=from_statement;
        }
        if(where_statement != null){
            SQL += where_statement;
        }
        return SQL;
    }

    private void set_WHERE_statement(Map<String, String> map_field_value) {
        for(String field: map_field_value.keySet()){
            where_statement += field + "=\"" + map_field_value.get(field) + "\", ";
        }
        where_statement = where_statement.substring(0, where_statement.length()-2);
    }
    private void set_SET_statement(Map<String, String> map_field_value) {
        for(String field: map_field_value.keySet()){
            set_statement += field + "=\"" + map_field_value.get(field) + "\", ";
        }
        set_statement = set_statement.substring(0, set_statement.length()-2);
    }
}
