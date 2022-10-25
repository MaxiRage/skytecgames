package Entity;

import Actions.JoiningClan;
import DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users extends Thread {
    private final String name_users = "PLAYER_";
    private final String name_clans = "PowerRangers";
    private final int skill_Arena = (int) (Math.random() * 10);
    private final int skill_Gamble = (int) (Math.random() * 30);
    private final int balances = 300;
    private static int count = 0;

    @SneakyThrows
    @Override
    public void run() {
        String nameUser = createNewUser();
        new JoiningClan().feeTreasury(nameUser);
    }

    private synchronized String createNewUser() throws SQLException {
        count++;
        String nameUser = name_users + count;
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequestCreate = String.format("INSERT INTO USERS (NAME_USERS, NAME_CLANS, SKILL_ARENA, SKILL_GAMBLE, BALANCES) " +
                    "VALUES ('%s', '%s', %d, %d, %d)", nameUser, name_clans, skill_Arena, skill_Gamble, balances);
            ManagementTables.statement(connection, SQLRequestCreate);

            System.out.println("����� " + Thread.currentThread() + " � ������: C����� user c name = " + nameUser);
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
}
