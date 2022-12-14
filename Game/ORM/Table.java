package Game.ORM;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class Table  implements Cloneable{
    Map<String, String> types;
    ResultSet data;

    public Table get_table (int id, String table) throws Exception {
        get_row(DbConnection.get_instance().get_row_by_id(id, table));
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public Table(){}

    protected void get_row(ResultSet data) throws Exception {
        this.types = DbConnection.get_instance().get_field_type_map();
        this.data = data;
        if(data.getClass() != NullResultSet.class) {
            Field[] fields = this.getClass().getFields();
            for(Field field: fields){
                if(!field.getName().startsWith("__")) {
                    set_field_value(field);
                }
            }
        }
    }

    public void get_row_by_attribute(String attribute, String value) throws Exception {
        DbConnection.get_instance().set_table(this.getClass().getSimpleName());
        get_row(DbConnection.get_instance().get_rows_by_attribute(attribute, value));
    }
    public void delete() throws NoSuchFieldException, IllegalAccessException, SQLException {
        Field Id = this.getClass().getDeclaredField("id");
        DbConnection.get_instance().delete((Integer) Id.get(this));
    }
    public void save() throws SQLException, IllegalAccessException {
        Field[] fields = this.getClass().getDeclaredFields();
        DbConnection.get_instance().update(fields, this);
    }

    public void insert() throws Exception {
        Field[] fields = this.getClass().getDeclaredFields();
        DbConnection.get_instance().set_table(this.getClass().getSimpleName());
        DbConnection.get_instance().insert(fields, this);
    }

    private void set_field_value(Field field) throws SQLException, IllegalAccessException {
        switch (types.get(field.getName().toLowerCase())) {
            case "INTEGER" -> set_integer_field(field);
            case "TEXT", "STRING", "VARCHAR" -> set_string_field(field);
            case "BOOLEAN" -> set_boolean_field(field);
        }
    }

    private void set_boolean_field(Field field) throws SQLException, IllegalAccessException{
        if (data.getString(field.getName()).trim().equalsIgnoreCase("true")){
            field.set(this, true);
        }else {
            field.set(this, false);
        }


    }
    private void set_string_field(Field field) throws SQLException, IllegalAccessException {
        field.set(this, data.getString(field.getName()));
    }

    private void set_integer_field(Field field) throws SQLException, IllegalAccessException {
        field.set(this, data.getInt(field.getName()));
    }
}
