package app.DB.Tables;

public abstract class AbstarctTable {
    public String nameTable;

    public String createTable() {
        System.out.println("CREATE TABLE " + nameTable);
        return "CREATE TABLE " + nameTable + " (" +
                nameTable + "_ID INTEGER AUTO_INCREMENT PRIMARY KEY, ";
    }

    public String dropTable() {
        System.out.println("DROP TABLE " + nameTable);

        return "DROP TABLE " +
                nameTable;
    }

    public String truncateTable() {
        System.out.println("TRUNCATE TABLE " + nameTable);

        return "SET REFERENTIAL_INTEGRITY FALSE; " +
                "TRUNCATE TABLE " +
                nameTable;
    }

}
