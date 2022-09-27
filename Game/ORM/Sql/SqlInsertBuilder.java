package Game.ORM.Sql;

import java.util.Map;

public class SqlInsertBuilder extends SqlBuilder{

    public SqlInsertBuilder(String Table, Map<String, String> map_field_value){
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
        this.SQL = String.format(" INSERT INTO %s (%s) VALUES (%s)", Table, fields.substring(0, fields.length()-2), values.substring(0, values.length()-2));

    }

}
