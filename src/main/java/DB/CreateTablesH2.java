package DB;

import DB.Model.DetailsTable;
import DB.Model.TreasuryTable;
import DB.Model.UsersTable;
import Entity.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesH2 {
//TODO Cоздать метод для создания таблиц и удаления

    public void createAllTables() throws SQLException {
        String jdbcURL = "jdbc:h2:~/dataBaseH2";
        Connection connection = DriverManager.getConnection(jdbcURL);

        System.out.println("Connected to H2 database.");

        String createTableUsers =
                UsersTable.createUserTable() + " ;" +
                        TreasuryTable.createTreasuryTable() + " ;" +
                        DetailsTable.createDetailsTable();

        Statement statement = connection.createStatement();

        statement.execute(createTableUsers);

        connection.close();
    }

    public void dropAllTable() throws SQLException {
        String jdbcURL = "jdbc:h2:~/dataBaseH2";
        Connection connection = DriverManager.getConnection(jdbcURL);

        System.out.println("Connected to H2 database.");

        String dropTableUsers =
                UsersTable.dropUserTable() + " ;" +
                        TreasuryTable.dropTreasuryTable() + " ;" +
                        DetailsTable.dropDetailsTable();

        Statement statement = connection.createStatement();

        statement.execute(dropTableUsers);

        connection.close();
    }

}