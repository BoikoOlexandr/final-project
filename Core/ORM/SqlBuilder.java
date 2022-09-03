package Core.ORM;

import java.util.Map;

public class SqlBuilder {
    private String SQL = "";

    public SqlBuilder Select(){
        SQL += "SELECT * ";
        return this;
    }

    public SqlBuilder Select(String field){
        SQL += "SELECT " + field + " ";
        return this;
    }

    public SqlBuilder Select(String[] fields){
        SQL += "SELECT ";
        set_fields(fields);
        return this;
    }

    public SqlBuilder from(String table_name){
        SQL += " FROM " + table_name + " ";
        return this;
    }

    public SqlBuilder where(String[] fields){
        SQL += " WHERE ";
        set_fields(fields);
        return this;
    }

    public SqlBuilder where(String field){
        SQL += " WHERE " + field + "=? ";
        return this;
    }

    public SqlBuilder where(Map<String, String> map_field_value){
        SQL += " WHERE ";
        set_fields_and_values(map_field_value);
        return this;
    }

    public SqlBuilder update(String table){
        SQL += "UPDATE " + table + " ";
        return this;
    }

    public SqlBuilder set(Map<String, String> field_value_map){
        SQL += " SET ";
        set_fields_and_values(field_value_map);
        return this;
    }

    public SqlBuilder delete(String table_name){
        SQL += "DELETE ";
        return this.from(table_name);
    }

    public String getSQL() {
        return SQL;
    }

    private void set_fields(String[] fields) {
        for(String field: fields){
            SQL += field + "=?, ";
        }
        SQL = SQL.substring(0, SQL.length()-2);
    }

    private void set_fields_and_values(Map<String, String> map_field_value) {
        for(String field: map_field_value.keySet()){
            SQL += field + "=\"" + map_field_value.get(field) + "\", ";
        }
        SQL = SQL.substring(0, SQL.length()-2);
    }
}
