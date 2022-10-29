package app.DB.Tables;

public class ClansTable extends AbstarctTable{
    String nameTable = "clans";
    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String nameClan = "name_clan";
        return super.createTable() +
                nameClan + " VARCHAR(50) NOT NULL)";
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
