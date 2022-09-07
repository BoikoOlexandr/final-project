package Game.ORM;

import org.sqlite.SQLiteConfig;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    public static final int OUT_OF_RANGE = 0;

    public void setTABLE(String TABLE) {
        this.TABLE = TABLE;
    }

    private String TABLE;
    private static DbConnection instance;
    private Connection connection;
    private final Logger logger;

    public static DbConnection get_instance(String URL, String Table) {
        if (instance == null) {

            instance = new DbConnection(URL, Table);
        }
        return instance;
    }

    public static DbConnection get_instance() {
        return instance;
    }

    private DbConnection(String URL, String Table) {
        this.TABLE = Table;
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
        int id = 0;
        for (Field field : fields) {
            if (field.getName().trim().equalsIgnoreCase("id")) {
                where_map.put(field.getName(), String.valueOf(field.get(obj)));
            } else {
                set_map.put(field.getName(), String.valueOf(field.get(obj)));
            }
        }
        int updated = connection.createStatement().executeUpdate(new SqlBuilder()
                .update(TABLE)
                .set(set_map)
                .where(where_map).getSQL());
        if (updated >= 1) {
            logger.log(Level.WARNING, String.format("Table %s is updated", TABLE));
        } else {
            logger.log(Level.WARNING, String.format("Table %s is not updated", TABLE));
        }


    }

    public void insert(Field[] fields, Table obj) throws IllegalAccessException, SQLException {
        Map<String, String> insert_map = new HashMap<>();
        int id = 0;
        for (Field field : fields) {
            if (!field.getName().trim().equals("id")) {
                insert_map.put(field.getName(), String.valueOf(field.get(obj)));
            }
        }
        System.out.println(new SqlBuilder().Insert(TABLE, insert_map).getSQL());
        int updated = connection.createStatement().executeUpdate(new SqlBuilder()
                .Insert(TABLE, insert_map).getSQL());
    }

    public Map<String, String> get_field_type_map() throws SQLException {
        ResultSetMetaData types = connection.createStatement()
                .executeQuery(new SqlBuilder()
                        .Select()
                        .from(TABLE).getSQL())
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
                    Select().
                    from(TABLE).
                    where("id").getSQL());
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.getInt("id") == OUT_OF_RANGE) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, String.format("Row number %d does not exist in database", id));
            return new NullResultSet();
        }
        return resultSet;
    }

    public ResultSet get_row_by_id(int id, String table) {
        this.TABLE = table;
        return this.get_row_by_id(id);
    }

    public ResultSet get_rows_by_attribute(String Attribute, String value) throws SQLException {
        return connection.createStatement().executeQuery(new SqlBuilder().
                Select().
                from(TABLE).
                where(Attribute, value).getSQL());
    }

    public void delete(int id) {
        Map<String, String> where_map = new HashMap<>();
        where_map.put("id", String.valueOf(id));
        String SQL = new SqlBuilder().delete(TABLE).where(where_map).getSQL();
    }

    public List<String> get_table_names() throws SQLException {
        List<String> table_names = new ArrayList<>();
        ResultSet res = connection.createStatement().executeQuery(new SqlBuilder().
                Select("name").
                from("sqlite_master").
                where("type", "table").getSQL());
        while (res.next()) {
            table_names.add(res.getString(1));
        }
        return table_names;
    }

    public int get_number_of_rows() throws SQLException {
        return connection.createStatement().executeQuery(new SqlBuilder().
                Select(" COUNT(*)").
                from(TABLE).getSQL()).getInt(1);
    }
}

class NullResultSet implements ResultSet {

    @Override
    public boolean next() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean wasNull() {
        return false;
    }

    @Override
    public String getString(int columnIndex) {
        return null;
    }

    @Override
    public boolean getBoolean(int columnIndex) {
        return false;
    }

    @Override
    public byte getByte(int columnIndex) {
        return 0;
    }

    @Override
    public short getShort(int columnIndex) {
        return 0;
    }

    @Override
    public int getInt(int columnIndex) {
        return 0;
    }

    @Override
    public long getLong(int columnIndex) {
        return 0;
    }

    @Override
    public float getFloat(int columnIndex) {
        return 0;
    }

    @Override
    public double getDouble(int columnIndex) {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) {
        return null;
    }

    @Override
    public byte[] getBytes(int columnIndex) {
        return new byte[0];
    }

    @Override
    public Date getDate(int columnIndex) {
        return null;
    }

    @Override
    public Time getTime(int columnIndex) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) {
        return null;
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) {
        return null;
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) {
        return null;
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) {
        return null;
    }

    @Override
    public String getString(String columnLabel) {
        return null;
    }

    @Override
    public boolean getBoolean(String columnLabel) {
        return false;
    }

    @Override
    public byte getByte(String columnLabel) {
        return 0;
    }

    @Override
    public short getShort(String columnLabel) {
        return 0;
    }

    @Override
    public int getInt(String columnLabel) {
        return 0;
    }

    @Override
    public long getLong(String columnLabel) {
        return 0;
    }

    @Override
    public float getFloat(String columnLabel) {
        return 0;
    }

    @Override
    public double getDouble(String columnLabel) {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) {
        return null;
    }

    @Override
    public byte[] getBytes(String columnLabel) {
        return new byte[0];
    }

    @Override
    public Date getDate(String columnLabel) {
        return null;
    }

    @Override
    public Time getTime(String columnLabel) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) {
        return null;
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) {
        return null;
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) {
        return null;
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) {
        return null;
    }

    @Override
    public SQLWarning getWarnings() {
        return null;
    }

    @Override
    public void clearWarnings() {

    }

    @Override
    public String getCursorName() {
        return null;
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return null;
    }

    @Override
    public Object getObject(int columnIndex) {
        return null;
    }

    @Override
    public Object getObject(String columnLabel) {
        return null;
    }

    @Override
    public int findColumn(String columnLabel) {
        return 0;
    }

    @Override
    public Reader getCharacterStream(int columnIndex) {
        return null;
    }

    @Override
    public Reader getCharacterStream(String columnLabel) {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) {
        return null;
    }

    @Override
    public boolean isBeforeFirst() {
        return false;
    }

    @Override
    public boolean isAfterLast() {
        return false;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public void beforeFirst() {

    }

    @Override
    public void afterLast() {

    }

    @Override
    public boolean first() {
        return false;
    }

    @Override
    public boolean last() {
        return false;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public boolean absolute(int row) {
        return false;
    }

    @Override
    public boolean relative(int rows) {
        return false;
    }

    @Override
    public boolean previous() {
        return false;
    }

    @Override
    public void setFetchDirection(int direction) {

    }

    @Override
    public int getFetchDirection() {
        return 0;
    }

    @Override
    public void setFetchSize(int rows) {

    }

    @Override
    public int getFetchSize() {
        return 0;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getConcurrency() {
        return 0;
    }

    @Override
    public boolean rowUpdated() {
        return false;
    }

    @Override
    public boolean rowInserted() {
        return false;
    }

    @Override
    public boolean rowDeleted() {
        return false;
    }

    @Override
    public void updateNull(int columnIndex) {

    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) {

    }

    @Override
    public void updateByte(int columnIndex, byte x) {

    }

    @Override
    public void updateShort(int columnIndex, short x) {

    }

    @Override
    public void updateInt(int columnIndex, int x) {

    }

    @Override
    public void updateLong(int columnIndex, long x) {

    }

    @Override
    public void updateFloat(int columnIndex, float x) {

    }

    @Override
    public void updateDouble(int columnIndex, double x) {

    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) {

    }

    @Override
    public void updateString(int columnIndex, String x) {

    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) {

    }

    @Override
    public void updateDate(int columnIndex, Date x) {

    }

    @Override
    public void updateTime(int columnIndex, Time x) {

    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) {

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) {

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) {

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) {

    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) {

    }

    @Override
    public void updateObject(int columnIndex, Object x) {

    }

    @Override
    public void updateNull(String columnLabel) {

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) {

    }

    @Override
    public void updateByte(String columnLabel, byte x) {

    }

    @Override
    public void updateShort(String columnLabel, short x) {

    }

    @Override
    public void updateInt(String columnLabel, int x) {

    }

    @Override
    public void updateLong(String columnLabel, long x) {

    }

    @Override
    public void updateFloat(String columnLabel, float x) {

    }

    @Override
    public void updateDouble(String columnLabel, double x) {

    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) {

    }

    @Override
    public void updateString(String columnLabel, String x) {

    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) {

    }

    @Override
    public void updateDate(String columnLabel, Date x) {

    }

    @Override
    public void updateTime(String columnLabel, Time x) {

    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) {

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) {

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) {

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) {

    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) {

    }

    @Override
    public void updateObject(String columnLabel, Object x) {

    }

    @Override
    public void insertRow() {

    }

    @Override
    public void updateRow() {

    }

    @Override
    public void deleteRow() {

    }

    @Override
    public void refreshRow() {

    }

    @Override
    public void cancelRowUpdates() {

    }

    @Override
    public void moveToInsertRow() {

    }

    @Override
    public void moveToCurrentRow() {

    }

    @Override
    public Statement getStatement() {
        return null;
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) {
        return null;
    }

    @Override
    public Ref getRef(int columnIndex) {
        return null;
    }

    @Override
    public Blob getBlob(int columnIndex) {
        return null;
    }

    @Override
    public Clob getClob(int columnIndex) {
        return null;
    }

    @Override
    public Array getArray(int columnIndex) {
        return null;
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) {
        return null;
    }

    @Override
    public Ref getRef(String columnLabel) {
        return null;
    }

    @Override
    public Blob getBlob(String columnLabel) {
        return null;
    }

    @Override
    public Clob getClob(String columnLabel) {
        return null;
    }

    @Override
    public Array getArray(String columnLabel) {
        return null;
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) {
        return null;
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) {
        return null;
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) {
        return null;
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) {
        return null;
    }

    @Override
    public URL getURL(int columnIndex) {
        return null;
    }

    @Override
    public URL getURL(String columnLabel) {
        return null;
    }

    @Override
    public void updateRef(int columnIndex, Ref x) {

    }

    @Override
    public void updateRef(String columnLabel, Ref x) {

    }

    @Override
    public void updateBlob(int columnIndex, Blob x) {

    }

    @Override
    public void updateBlob(String columnLabel, Blob x) {

    }

    @Override
    public void updateClob(int columnIndex, Clob x) {

    }

    @Override
    public void updateClob(String columnLabel, Clob x) {

    }

    @Override
    public void updateArray(int columnIndex, Array x) {

    }

    @Override
    public void updateArray(String columnLabel, Array x) {

    }

    @Override
    public RowId getRowId(int columnIndex) {
        return null;
    }

    @Override
    public RowId getRowId(String columnLabel) {
        return null;
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) {

    }

    @Override
    public void updateRowId(String columnLabel, RowId x) {

    }

    @Override
    public int getHoldability() {
        return 0;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void updateNString(int columnIndex, String nString) {

    }

    @Override
    public void updateNString(String columnLabel, String nString) {

    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) {

    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) {

    }

    @Override
    public NClob getNClob(int columnIndex) {
        return null;
    }

    @Override
    public NClob getNClob(String columnLabel) {
        return null;
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) {
        return null;
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) {
        return null;
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) {

    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) {

    }

    @Override
    public String getNString(int columnIndex) {
        return null;
    }

    @Override
    public String getNString(String columnLabel) {
        return null;
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) {
        return null;
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) {
        return null;
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) {

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) {

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) {

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) {

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) {

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) {

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) {

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) {

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) {

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) {

    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) {

    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) {

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) {

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) {

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) {

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) {

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) {

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) {

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) {

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) {

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) {

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) {

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) {

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) {

    }

    @Override
    public void updateClob(int columnIndex, Reader reader) {

    }

    @Override
    public void updateClob(String columnLabel, Reader reader) {

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) {

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) {

    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) {
        return null;
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }
}