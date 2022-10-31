package app.DB;

import app.DB.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagementTables {
    AbstarctTable treasuryTable = new TreasuryTable();
    AbstarctTable usersTable = new UsersTable();
    AbstarctTable detailsTable = new DetailsTable();
    AbstarctTable clansTable = new ClansTable();

    public void createAllTables() {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    clansTable.createTable() + "; " +
                            usersTable.createTable() + "; " +
                            treasuryTable.createTable() + "; " +
                            detailsTable.createTable();
            statement(connection, SQLRequest);
        } catch (SQLException e) {
            dropAllTable();
            createAllTables();
        }
    }

    public void dropAllTable() {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    detailsTable.dropTable() + "; " +
                            treasuryTable.dropTable() + "; " +
                            usersTable.dropTable() + "; " +
                            clansTable.dropTable();
            statement(connection, SQLRequest);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void truncateAllTable()  {
        try (Connection connection = getConnection()) {

            String SQLRequest =
                    detailsTable.truncateTable() + "; " +
                            usersTable.truncateTable() + "; " +
                            clansTable.truncateTable() + "; " +
                            treasuryTable.truncateTable();

            statement(connection, SQLRequest);

        } catch (SQLException e) {
            throw new RuntimeException(e);
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