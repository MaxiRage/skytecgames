package app.DB.Tables;

public class DetailsTable extends AbstarctTable {

    String nameTable = "details";

    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String timeTransaction = "time_Transaction";
        final String usersId = "users_id";
        final String action = "action";
        final String amount = "amount";
        final String treasuryId = "treasury_id";
        final String balancesBefore = "balances_before";
        final String balancesAfter = "balances_after";
        return super.createTable() +
                timeTransaction + " TIMESTAMP NOT NULL, " +
                usersId + " INTEGER NOT NULL, " +
                action + " VARCHAR(50) NOT NULL, " +
                amount + " DECIMAL (10, 2) NOT NULL, " +
                treasuryId + " INTEGER NOT NULL, " +
                balancesBefore + " INTEGER NOT NULL, " +
                balancesAfter + " VARCHAR(50) NOT NULL, " +
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
