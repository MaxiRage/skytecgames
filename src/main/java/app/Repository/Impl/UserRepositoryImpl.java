package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.UserRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {

    @SneakyThrows
    @Override
    public synchronized void createNewUser(String nameUser, int count, String nameClan, int skillArena, int skillGamble, int balances) {
        String fullNameUser = nameUser + count;
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequestCreate = String.format("INSERT INTO USERS (NAME_USERS, NAME_CLANS, SKILL_ARENA, SKILL_GAMBLE, BALANCES) " +
                    "VALUES ('%s', '%s', %d, %d, %d)", fullNameUser, nameClan, skillArena, skillGamble, balances);
            ManagementTables.statement(connection, SQLRequestCreate);

            System.out.println(Thread.currentThread() + " в работе: Cоздан user c name = " + fullNameUser);
        }
    }

    @SneakyThrows
    @Override
    public synchronized String getUserName(int idUser) {
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

    @SneakyThrows
    @Override
    public synchronized int getUserId(String nameUser) {
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
    @Override
    public synchronized int getUserBalance(String nameUser) {
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
    @Override
    public synchronized int getSkillArena(String nameUser) {
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
    @Override
    public synchronized void updateBalanceUsers(int amount, String nameUser) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE users SET balances=balances-%d WHERE name_users = '%s'", amount, nameUser);
            ManagementTables.statement(connection, SQLRequest);
        }
    }

    @SneakyThrows
    @Override
    public synchronized int getCountRowsUsersTable() {
        int count = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = "SELECT COUNT (*) FROM users";
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            while (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
            resultSet.close();
        }
        return count;
    }

    @SneakyThrows
    @Override
    public synchronized boolean checkIsPresentUserInBD(String nameUsers) {
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("SELECT name_users FROM users where name_users = '%s'", nameUsers);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if (resultSet.next()) {
                resultSet.close();
                return true;
            }
            resultSet.close();
        }
        return false;
    }
}