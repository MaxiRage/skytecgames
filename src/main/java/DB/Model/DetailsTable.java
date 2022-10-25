package DB.Model;

public class DetailsTable extends AbstarctTable {

    String nameTable = "details";
    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String timeTransaction = "timeTransaction";
        final String users_id = "users_id";
        final String action = "action";
        final String amount = "amount";
        final String treasury_id = "treasury_id";
        return super.createTable() +
                timeTransaction + " TIMESTAMP NOT NULL, " +
                users_id + " INTEGER NOT NULL, " +
                action + " VARCHAR(50) NOT NULL, " +
                amount + " DECIMAL (10, 2) NOT NULL, " +
                treasury_id + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + users_id + ") REFERENCES Users (" + users_id + ")," +
                "FOREIGN KEY (" + treasury_id + ") REFERENCES Treasury (" + treasury_id + "))";
    }

    @Override
    public String dropTable() {
        super.nameTable = this.nameTable;
        return super.dropTable();
    }

    @Override
    public String truncateTable() {
        super.nameTable = this.nameTable;
        return super.truncateTable();
    }
}
