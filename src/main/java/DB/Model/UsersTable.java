package DB.Model;

public class UsersTable extends AbstarctTable {
    String nameTable = "users";

    @Override
    public String createTable() {
        super.nameTable = this.nameTable;
        final String nameUsers = "name_Users";
        final String nameClan = "name_Clans";
        final String skillArena = "skill_Arena";
        final String skillGamble = "skill_Gamble";
        final String balance = "balances";

        return super.createTable() +
                nameUsers + " VARCHAR(50) NOT NULL UNIQUE, " +
                nameClan + " VARCHAR(50) NOT NULL, " +
                skillArena + " INTEGER NOT NULL, " +
                skillGamble + " INTEGER NOT NULL, " +
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