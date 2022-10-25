package DB.Model;

public class TreasuryTable extends AbstarctTable {
    String nameTable = "treasury";

    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String balance = "balances";
        final String nameClan = "name_Clans";
        return super.createTable() +
                nameClan + " VARCHAR(50) NOT NULL, " +
                balance + " DECIMAL(10,2) NOT NULL)";
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
