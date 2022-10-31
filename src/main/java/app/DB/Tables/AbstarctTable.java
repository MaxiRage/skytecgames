package app.DB.Tables;

public abstract class AbstarctTable {
    public String nameTable;

    public String createTable() {
        return "CREATE TABLE " + nameTable + " (" +
                nameTable + "_ID INTEGER AUTO_INCREMENT PRIMARY KEY, ";
    }

    public String dropTable() {
        return "DROP TABLE " +
                nameTable;
    }

    public String truncateTable() {
        return "SET REFERENTIAL_INTEGRITY FALSE; " +
                "TRUNCATE TABLE " +
                nameTable;
    }

}
