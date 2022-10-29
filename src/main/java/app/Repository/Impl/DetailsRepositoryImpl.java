package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.UserRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DetailsRepositoryImpl implements app.Repository.DetailsRepository {
    UserRepository userRepository;
    @Override
    public synchronized void insertTo(String nameUser, String actions, int amount, int idTreasury) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                    "VALUES ('%s', %d, '%s', %d, %d)", LocalDateTime.now(), userRepository.getUserId(nameUser), actions, amount, idTreasury);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " в работе: Не удалось внести детали транзакции для пользователя" + nameUser);
        }
    }
}
