package Entity;

import DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class Treasury extends Thread{
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
            System.out.println("����� " + Thread.currentThread() + " � ������: ������� �����, �����c = " + balances);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
    private synchronized void feeTreasury (int amount) {

    }
}
