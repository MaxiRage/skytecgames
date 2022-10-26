package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.TreasuryRepository;
import lombok.SneakyThrows;

import java.sql.Connection;

public class TreasuryRepositoryImpl implements TreasuryRepository {

    @Override
    @SneakyThrows
    public synchronized void createNewTreasury(String nameClan, int balances) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO TREASURY (NAME_CLANS, BALANCES) VALUES ('%s', %d)",
                    nameClan, balances);
            System.out.println("Поток " + Thread.currentThread() + " в работе: Создана КАЗНА, баланc = " + balances);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
    @SneakyThrows
    @Override
    public synchronized void updateBalanceTreasury(int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances+%d WHERE TREASURY_ID = 1", amount);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}