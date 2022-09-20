package Game.ORM;

import org.sqlite.SQLiteConfig;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    public static final int OUT_OF_RANGE = 0;
    private String table;
    private static DbConnection instance;
    private Connection connection;
    private final Logger logger;

    public static void get_instance(String URL, String Table) {
        if (instance == null) {
            instance = new DbConnection(URL, Table);
        }
    }

    public static DbConnection get_instance() {
        return instance;
    }

    private DbConnection(String URL, String Table) {
        this.table = Table;
        logger = Logger.getLogger(this.getClass().getSimpleName());
        SQLiteConfig config = new SQLiteConfig();
        config.setEncoding(SQLiteConfig.Encoding.UTF_8);
        try {
            this.connection = DriverManager.getConnection(URL, config.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Field[] fields, Table obj) throws IllegalAccessException, SQLException {
        Map<String, String> set_map = new HashMap<>();
        Map<String, String> where_map = new HashMap<>();
        for (Field field : fields) {
            if (field.getName().trim().equalsIgnoreCase("id")) {
                where_map.put(field.getName(), String.valueOf(field.get(obj)));
            } else {
                set_map.put(field.getName(), String.valueOf(field.get(obj)));
            }
        }
        int updated = connection.createStatement().executeUpdate(new SqlBuilder()
                .update(table)
                .set(set_map)
                .where(where_map).get_SQL());
        if (updated >= 1) {
            logger.log(Level.WARNING, String.format("Table %s is updated", table));
        } else {
            logger.log(Level.WARNING, String.format("Table %s is not updated", table));
        }
    }

    public void insert(Field[] fields, Table obj) throws Exception {
        Map<String, String> insert_map = new HashMap<>();
        for (Field field : fields) {
            if (!field.getName().trim().equals("id")) {
                insert_map.put(field.getName(), String.valueOf(field.get(obj)));
            }
        }
        connection.createStatement().executeUpdate(new SqlBuilder().insert(table, insert_map).get_SQL());
    }

    public Map<String, String> get_field_type_map() throws SQLException {
        ResultSetMetaData types = connection.createStatement()
                .executeQuery(new SqlBuilder()
                        .select()
                        .from(table).get_SQL())
                .getMetaData();
        Map<String, String> field_type_map = new HashMap<>();
        for (int i = 1; i < types.getColumnCount() + 1; i++) {
            field_type_map.put(types.getColumnName(i), types.getColumnTypeName(i));
        }
        return field_type_map;
    }

    public ResultSet get_row_by_id(int id) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(new SqlBuilder().
                    select().
                    from(table).
                    where("id").get_SQL());
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.getInt("id") == OUT_OF_RANGE) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, String.format("Row number %d does not exist in %s database", id, table));
            return new NullResultSet();
        }
        return resultSet;
    }

    public ResultSet get_row_by_id(int id, String table) {
        this.table = table;
        return this.get_row_by_id(id);
    }

    public ResultSet get_rows_by_attribute(String Attribute, String value) throws SQLException {
        return connection.createStatement().executeQuery(new SqlBuilder().
                select().
                from(table).
                where(Attribute, value).get_SQL());
    }

    public void delete(int id) throws SQLException {
        Map<String, String> where_map = new HashMap<>();
        where_map.put("id", String.valueOf(id));
        String SQL = new SqlBuilder().delete(table).where(where_map).get_SQL();
        connection.createStatement().executeUpdate(SQL);
    }

    public List<String> get_table_names() throws SQLException {
        List<String> table_names = new ArrayList<>();
        ResultSet res = connection.createStatement().executeQuery(new SqlBuilder().
                select("name").
                from("sqlite_master").
                where("type", "table").get_SQL());
        while (res.next()) {
            table_names.add(res.getString(1));
        }
        return table_names;
    }

    public int get_number_of_rows() throws SQLException {
        return connection.createStatement().executeQuery(new SqlBuilder().
                select(" COUNT(*)").
                from(table).get_SQL()).getInt(1);
    }

    public void set_table(String table) {
        this.table = table;
    }
}
