package app.DB.Tables;

public class UsersTable extends AbstarctTable {
    String nameTable = "users";

    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String nameUsers = "name_Users";
        final String idClans = "clans_id";
        final String skillArena = "skill_Arena";
        final String skillGamble = "skill_Gamble";
        final String balance = "balances";

        return super.createTable() +
                nameUsers + " VARCHAR(50) NOT NULL UNIQUE, " +
                idClans + " INTEGER, " +
                skillArena + " INTEGER NOT NULL, " +
                skillGamble + " INTEGER NOT NULL, " +
                balance + " DECIMAL(10,2) NOT NULL, " +
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