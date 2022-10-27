package app.DB.Model;

public class DetailsTable extends AbstarctTable {

    String nameTable = "details";
    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String timeTransaction = "timeTransaction";
        final String usersId = "users_id";
        final String action = "action";
        final String amount = "amount";
        final String treasuryId = "treasury_id";
        return super.createTable() +
                timeTransaction + " TIMESTAMP NOT NULL, " +
                usersId + " INTEGER NOT NULL, " +
                action + " VARCHAR(50) NOT NULL, " +
                amount + " DECIMAL (10, 2) NOT NULL, " +
                treasuryId + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + usersId + ") REFERENCES Users (" + usersId + ")," +
                "FOREIGN KEY (" + treasuryId + ") REFERENCES Treasury (" + treasuryId + "))";
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
