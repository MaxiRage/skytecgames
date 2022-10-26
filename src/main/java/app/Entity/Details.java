package app.Entity;

import app.DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.time.LocalDateTime;

/***
 * Статику на 100 - цикл - оплата
 */
public class Details {
    @SneakyThrows
    public synchronized void insertTo(String nameUser, String actions, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                    "VALUES ('%s', %d, '%s', %d, %d)", LocalDateTime.now(), new Users().getUserId(nameUser), actions, amount, 1);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}
