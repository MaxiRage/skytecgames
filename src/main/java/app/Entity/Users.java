package app.Entity;

import app.DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users extends Thread {
    private final String name_users = "Player_";
    private final String name_clans = "PowerRangers";
    private final int skill_Arena = (int) (Math.random() * 10) + 1;
    private final int skill_Gamble = (int) (Math.random() * 30) + 1;
    private final int balances = 300;
    private static int count = 0;

    @SneakyThrows
    @Override
    public void run() {
        String nameUser = createNewUser();
    }

    private synchronized String createNewUser() throws SQLException {
        count++;
        String nameUser = name_users + count;
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequestCreate = String.format("INSERT INTO USERS (NAME_USERS, NAME_CLANS, SKILL_ARENA, SKILL_GAMBLE, BALANCES) " +
                    "VALUES ('%s', '%s', %d, %d, %d)", nameUser, name_clans, skill_Arena, skill_Gamble, balances);
            ManagementTables.statement(connection, SQLRequestCreate);

            System.out.println(Thread.currentThread() + " в работе: Cоздан user c name = " + nameUser);
        }
        return nameUser;
    }
    public String getUserName(int idUser) throws SQLException {
        String nameUser = "";
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT name_users FROM users " +
                    "WHERE users_id = %d", idUser);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                nameUser = resultSet.getString("name_users");
            }

            resultSet.close();
        }
        return nameUser;
    }
    public int getUserId(String nameUser) throws SQLException {
        int user_id = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT users_id FROM users " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                user_id = resultSet.getInt("users_id");
            }

            resultSet.close();
        }
        return user_id;
    }

    @SneakyThrows
    public int getUserBalance(String nameUser) {
        int balanceUser = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT balances FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                balanceUser = resultSet.getInt("balances");
            }

            resultSet.close();
        }
        return balanceUser;
    }

    @SneakyThrows
    public int getSkillArena(String nameUser) {
        int skillArena = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT skill_arena FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                skillArena = resultSet.getInt("skill_arena");
            }

            resultSet.close();
        }
        return skillArena;
    }

    @SneakyThrows
    public synchronized void updateBalanceUsers(int amount, String nameUser) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE users SET balances=balances-%d WHERE name_users = '%s'", amount, nameUser);
            ManagementTables.statement(connection, SQLRequest);
        }
    }

//    @SneakyThrows
//    public synchronized int getCountRowsUsersTable() {
//        int count = 0;
//        try (Connection connection = ManagementTables.getConnection()) {
//            Statement statement = connection.createStatement();
//            String SQLRequest = "SELECT COUNT (*) FROM users";
//            ResultSet resultSet = statement.executeQuery(SQLRequest);
//
//            while (resultSet.next()) {
//                count = resultSet.getInt("count(*)");
//            }
//            resultSet.close();
//        }
//        return count;
//    }
}
