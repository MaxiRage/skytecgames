package app.DB.Model;

public class ClansTable extends AbstarctTable{
    String nameTable = "clans";
    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String nameClan = "name_clan";
        final String balance = "balance";
        return super.createTable() +
                nameClan + " VARCHAR(50) NOT NULL, " +
                balance + " DECIMAL (10, 2) NOT NULL)";
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
