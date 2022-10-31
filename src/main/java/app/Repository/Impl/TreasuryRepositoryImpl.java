package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.TreasuryRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public synchronized void increaseBalanceTreasury(int idTreasury, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances+%d WHERE TREASURY_ID = %d", amount, idTreasury);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void reduceBalanceTreasury(int idTreasury, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE treasury SET balances=balances-%d WHERE TREASURY_ID = %d", amount, idTreasury);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public synchronized int getBalance(int idTreasury) {
        int balance = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("SELECT balances FROM TREASURY WHERE treasury_id =%d", idTreasury);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if (resultSet.next()) {
                balance = resultSet.getInt("balances");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    @Override
    public synchronized int getIdTreasury(int idClan) {
        int IdTreasury = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("SELECT TREASURY_ID FROM TREASURY WHERE CLANS_ID =%d", idClan);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if (resultSet.next()) {
                IdTreasury = resultSet.getInt("TREASURY_ID");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return IdTreasury;
    }
}