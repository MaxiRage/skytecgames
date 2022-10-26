package app.Entity;

import app.DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class Treasury extends Thread {
    private final String name_clans = "PowerRangers";
    private final int balances = 0;

    @SneakyThrows
    @Override
    public void run() {
        createNewTreasury();
    }

    private synchronized void createNewTreasury() throws SQLException {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO TREASURY (NAME_CLANS, BALANCES) VALUES ('%s', %d)",
                    name_clans, balances);
            System.out.println("Поток " + Thread.currentThread() + " в работе: Создана КАЗНА, баланc = " + balances);
            ManagementTables.statement(connection, SQLRequest);
        }
    }

    @SneakyThrows
    public synchronized void updateBalanceTreasury(int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances+%d WHERE TREASURY_ID = 1", amount);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}
