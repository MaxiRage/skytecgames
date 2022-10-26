package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.UserRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.time.LocalDateTime;

public class DetailsRepositoryImpl implements app.Repository.DetailsRepository {
    UserRepository userRepository;
    @SneakyThrows
    @Override
    public synchronized void insertTo(String nameUser, String actions, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                    "VALUES ('%s', %d, '%s', %d, %d)", LocalDateTime.now(), userRepository.getUserId(nameUser), actions, amount, 1);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}
