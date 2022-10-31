package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

public class DetailsRepositoryImpl implements app.Repository.DetailsRepository {
    UserRepository userRepository = new UserRepositoryImpl();
    TreasuryRepository treasuryRepository = new TreasuryRepositoryImpl();

    @Override
    public synchronized void insertTo(String nameUser, String actions, int amount, int idTreasury) {
        try (Connection connection = ManagementTables.getConnection()) {
            int balanceBefore = treasuryRepository.getBalance(idTreasury);
            if (Objects.equals(actions, "JOINING_CLAN") || Objects.equals(actions, "BATTLES_DEDUCT") || Objects.equals(actions, "GAMBLES_DEDUCT")
                    || Objects.equals(actions, "TASKS_DEDUCT") || Objects.equals(actions, "HUNTING_DEDUCT"))

                treasuryRepository.increaseBalanceTreasury(idTreasury, amount);

            int balanceAfter = treasuryRepository.getBalance(idTreasury);

            String SQLRequest = String.format("INSERT INTO details (time_transaction, users_id, action, amount, treasury_id, balances_before, balances_after) " +
                    "VALUES ('%s', %d, '%s', %d, %d, %d, %d)", LocalDateTime.now(), userRepository.getUserId(nameUser), actions, amount, idTreasury, balanceBefore, balanceAfter);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}