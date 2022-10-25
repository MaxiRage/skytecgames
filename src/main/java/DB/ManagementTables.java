package DB;

import DB.Model.DetailsTable;
import DB.Model.TreasuryTable;
import DB.Model.UsersTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagementTables {
    TreasuryTable treasuryTable = new TreasuryTable();
    UsersTable usersTable = new UsersTable();
    DetailsTable detailsTable = new DetailsTable();

    public void createAllTables(String... tables) throws SQLException {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    usersTable.createTable() + "; " +
                            treasuryTable.createTable() + "; " +
                            detailsTable.createTable();
            statement(connection, SQLRequest);
        }
    }

    public void dropAllTable() throws SQLException {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    detailsTable.dropTable() + "; " +
                            usersTable.dropTable() + "; " +
                            treasuryTable.dropTable();
            statement(connection, SQLRequest);

        }
    }

    public void truncateAllTable() throws SQLException {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    detailsTable.truncateTable() + "; " +
                            usersTable.truncateTable() + "; " +
                            treasuryTable.truncateTable();

            statement(connection, SQLRequest);

        }
    }

    public static void statement(Connection connection, String SQLRequest) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(SQLRequest);
    }

    public static Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:h2:~/dataBaseH2;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
        return DriverManager.getConnection(jdbcURL);
    }
}