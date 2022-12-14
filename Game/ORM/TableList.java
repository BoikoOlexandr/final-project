package Game.ORM;

import java.util.ArrayList;

public abstract class TableList {
    protected ArrayList<Table> rows = new ArrayList<>();

    public TableList(Table table) throws Exception {
        String table_name  = table.getClass().getSimpleName();
        DbConnection.get_instance().set_table(table_name);
        int number_of_rows = DbConnection.get_instance().get_number_of_rows();
        for (int id=1; id <= number_of_rows; id++){
            table = (Table) table.clone();
            rows.add(table.get_table(id, table_name));
        }
    }

}
