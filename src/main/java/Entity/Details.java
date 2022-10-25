package Entity;

import DB.ManagementTables;
import Entity.Users;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Details {

    @SneakyThrows
    public void insertTo(String nameUser, String actions, int amount) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                    "VALUES ('%s', %d, '%s', %d, %d)", LocalDateTime.now(), new Users().getUserId(nameUser), actions, amount, 1);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}
