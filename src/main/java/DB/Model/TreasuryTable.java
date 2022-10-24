package DB.Model;

public class TreasuryTable {
    private static final String nameTable = "treasury";
    private static final String nameClan = "name_Clans";
    private static final String balance = "balances";

    public static String createTreasuryTable() {

        System.out.println("CREATE TABLE Treasury");

        return "CREATE TABLE " + nameTable + " (" +
                nameTable + "_ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                nameClan + " VARCHAR(50) NOT NULL, " +
                balance + " DECIMAL(15,2) NOT NULL)";
    }

    public static String dropTreasuryTable() {
        System.out.println("DROP TABLE Treasury");

        return "DROP TABLE " +
                nameTable;
    }
}
