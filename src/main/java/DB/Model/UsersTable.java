package DB.Model;

import lombok.Getter;

/*
Создание таблицы пользователей
 */
public class UsersTable {
    private static final String nameTable = "users";
    private static final String nameUsers = "name_Users";
    private static final String nameClan = "name_Clans";
    private static final String skillArena = "skill_Arena";
    private static final String skillGamble = "skill_Gamble";
    private static final String balance = "balances";

    public static String createUserTable() {

        System.out.println("CREATE TABLE Users");

        return "CREATE TABLE " + nameTable + " (" +
                nameTable + "_ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                nameUsers + " VARCHAR(50) NOT NULL, " +
                nameClan + " VARCHAR(50) NOT NULL, " +
                skillArena + " INTEGER NOT NULL, " +
                skillGamble + " INTEGER NOT NULL, " +
                balance + " DECIMAL(15,2) NOT NULL)";
    }

    public static String dropUserTable() {
        System.out.println("DROP TABLE Users");

        return "DROP TABLE " +
                nameTable;
    }
}