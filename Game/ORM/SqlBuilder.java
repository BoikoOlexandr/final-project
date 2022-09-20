package Game.ORM;

import java.util.Map;

public class SqlBuilder {
    private String SQL = "";

    public SqlBuilder select(){
        SQL += "SELECT * ";
        return this;
    }

    public SqlBuilder insert(String Table, Map<String, String> map_field_value){
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for(String field: map_field_value.keySet()){
            fields
                    .append(field)
                    .append(", ");
            values
                    .append("\"")
                    .append(map_field_value.get(field))
                    .append("\", ");
        }
        SQL += String.format(" INSERT INTO %s (%s) VALUES (%s)", Table, fields.substring(0, fields.length()-2), values.substring(0, values.length()-2));

        return this;
    }
    public SqlBuilder select(String field){
        SQL += "SELECT " + field + " ";
        return this;
    }

    public SqlBuilder select(String[] fields){
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
    public SqlBuilder where(String field, String value){
        SQL += " WHERE " + field + "=\"" + value +"\" ";
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

    public String get_SQL() {
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
