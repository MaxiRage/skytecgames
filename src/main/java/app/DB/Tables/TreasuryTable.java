package app.DB.Tables;

public class TreasuryTable extends AbstarctTable {
    String nameTable = "treasury";

    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String balance = "balances";
        final String idClans = "clans_id";
        return super.createTable() +
                idClans + " INTEGER NOT NULL, " +
                balance + " DECIMAL(10,2) NOT NULL," +
                "FOREIGN KEY (" + idClans + ") REFERENCES Clans (" + idClans + "))";
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
