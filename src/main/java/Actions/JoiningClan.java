package Actions;

import DB.ManagementTables;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.time.LocalDateTime;

public class JoiningClan extends Thread{

    @SneakyThrows
    public synchronized static void feeTreasury(int users_id) {
        try (Connection connection = ManagementTables.getConnection()) {

            double amountForJoining = 25;
            String balanceUser = String.format("SELECT balances FROM USERS " +
                    "WHERE users_id = %d", users_id); // TODO Сделать возврат суммы
            ManagementTables.statement(connection, balanceUser);

            System.out.println(balanceUser);
            if (Double.parseDouble(balanceUser) > amountForJoining) {
                String SQLRequestUsers = String.format("UPDATE users SET balances=balances-%f " +
                        "WHERE users_id = 1", amountForJoining);
                String SQLRequestTreasury = String.format("UPDATE treasure SET balances=balances+%f " +
                        "WHERE TREASURY_ID = 1", amountForJoining);
                String SQLRequestDetails = String.format("INSERT INTO details (timetransaction, users_id, action, amount, treasury_id) " +
                        "VALUES ('%s', %d, '%s', %f, %d)", LocalDateTime.now(), users_id, EnamActions.JOININGCLAN, amountForJoining, 1);
                String SQLRequest = SQLRequestUsers + "; "
                        + SQLRequestTreasury + "; "
                        + SQLRequestDetails;
                ManagementTables.statement(connection, SQLRequest);
            }
            else System.out.println("Не хватает средств на членство");

            System.out.printf("Поток " + Thread.currentThread() + " в работе: " +
                    "Юзер %d выполнил %s и заплатил %d в КАЗНУ \n"
                    , users_id, EnamActions.JOININGCLAN, amountForJoining);

        }
    }

    @Override
    public void run() {
        feeTreasury(1);
    }

    public static void main(String[] args) {
        new JoiningClan().start();
    }
}
