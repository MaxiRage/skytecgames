package Entity;

import DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class Users  extends Thread {
    private final String name_users = "player_";
    private final String name_clans = "PowerRangers";
    private final int skill_Arena = (int) (Math.random() * 10);
    private final int skill_Gamble = (int) (Math.random() * 30);
    private final int balances = 300;
    private static int count = 0;

    public Users() throws SQLException {
    }

    @SneakyThrows
    @Override
    public void run() {
        createNewUser();
    }

    private synchronized void createNewUser() throws SQLException {
        try (Connection connection = ManagementTables.getConnection()) {
            count++;
            String nameUser = name_users + count;
            String SQLRequest = String.format("INSERT INTO USERS (NAME_USERS, NAME_CLANS, SKILL_ARENA, SKILL_GAMBLE, BALANCES) " +
                    "VALUES ('%s', '%s', %d, %d, %d)", nameUser, name_clans, skill_Arena, skill_Gamble, balances);

            System.out.println("Поток " + Thread.currentThread() + " в работе: Cоздал user c name = " + nameUser);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}
