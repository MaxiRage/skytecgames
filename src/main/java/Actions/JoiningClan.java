package Actions;

import DB.ManagementTables;
import Entity.Users;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class JoiningClan {
    int amountForJoining = 25;

    @SneakyThrows
    public void feeTreasury(String nameUser) {

        try (Connection connection = ManagementTables.getConnection()) {

            double balanceUser = getUserBalance(nameUser);

            if (balanceUser > amountForJoining) {
                String SQLRequestUsers = String.format("UPDATE users SET balances=balances-%d " +
                        "WHERE name_users = '%s'", amountForJoining, nameUser);
                String SQLRequestTreasury = String.format("UPDATE treasury SET balances=balances+%d " +
                        "WHERE TREASURY_ID = 1", amountForJoining);
                String SQLRequestDetails = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                        "VALUES ('%s', %d, '%s', %d, %d)", LocalDateTime.now(), new Users().getUserId(nameUser), EnamActions.JOININGCLAN, amountForJoining, 1);
                String SQLRequest = SQLRequestUsers + "; "
                        + SQLRequestTreasury + "; "
                        + SQLRequestDetails;
                ManagementTables.statement(connection, SQLRequest);
            } else System.out.println("Не хватает средств на членство");

            System.out.printf("Поток " + Thread.currentThread() + " в работе: " +
                            "Пользователь %s вступил в клан \"PowerRangers\" и заплатил %d в КАЗНУ \n"
                    , nameUser, amountForJoining);
        }
    }

    public double getUserBalance(String nameUser) throws SQLException {
        double balanceUser = 0;
        try (Connection connection = ManagementTables.getConnection()) {

            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT balances FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                balanceUser = resultSet.getInt("balances");
            }

            resultSet.close();
        }
        return balanceUser;
    }
}
