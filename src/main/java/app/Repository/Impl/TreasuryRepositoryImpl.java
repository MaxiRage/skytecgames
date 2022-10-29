package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.TreasuryRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class TreasuryRepositoryImpl implements TreasuryRepository {

    @Override
    public synchronized void createNewTreasury(int nameClan, int balances) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO TREASURY (NAME_CLANS, BALANCES) VALUES ('%s', %d)",
                    nameClan, balances);
            System.out.println("Поток " + Thread.currentThread() + " в работе: Создана КАЗНА для клана " + nameClan + " , баланc = " + balances);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " в работе: Не удалось создать новую Казну для Клана " + nameClan);
        }
    }
    @Override
    public synchronized void increaseBalanceTreasury(int idClan, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances+%d WHERE TREASURY_ID = %d", amount, idClan);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " в работе: Не удалось пополнить казну клана " + idClan);
        }
    }

    @Override
    public void reduceBalanceTreasury(int idClan, int amount) {

    }
}