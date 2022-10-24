package DB.Model;

public class DetailsTable {
    private static final String nameTable = "details";
    private static final String timeTransaction = "timeTransaction";
    private static final String users_id = "users_id";
    private static final String action = "action";
    private static final String amount = "amount";
    private static final String treasury_id = "treasury_id"; // FK Treasure

    public static String createDetailsTable() {

        System.out.println("CREATE TABLE Details");

        return "CREATE TABLE " + nameTable + " (" +
                nameTable + "_ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                timeTransaction + " TIMESTAMP NOT NULL, " +
                users_id + " INTEGER NOT NULL, " +
                action + " VARCHAR(50) NOT NULL, " +
                amount + " INTEGER NOT NULL, " +
                treasury_id + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + users_id + ") REFERENCES Users (" + users_id + ")," +
                "FOREIGN KEY (" + treasury_id + ") REFERENCES Treasury (" + treasury_id + "))";
    }

    public static String dropDetailsTable() {
        System.out.println("DROP TABLE Details");

        return "DROP TABLE " +
                nameTable;
    }
}
