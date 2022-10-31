package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.TreasuryRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class TreasuryRepositoryImpl implements TreasuryRepository {

    @Override
    public synchronized void createNewTreasury(int idClan, String nameClan) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO TREASURY (clans_id, balances) VALUES ('%s', %d)",
                    idClan, 0);
            System.out.println("Поток " + Thread.currentThread() + " in work: create treasury for clan " + nameClan);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void increaseBalanceTreasury(int idClan, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances+%d WHERE TREASURY_ID = %d", amount, idClan);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void reduceBalanceTreasury(int idClan, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances-%d WHERE TREASURY_ID = %d", amount, idClan);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}