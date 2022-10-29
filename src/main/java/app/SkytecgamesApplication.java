package app;

import app.DB.ManagementTables;
import app.Service.Impl.UserServiceImpl;
import app.Utility.GameApplication;

import java.sql.SQLException;
/***
 * ”кажите количество создаваемых пользователей в переменной "countUsers"
 */
//TODO всЄ на английский

public class SkytecgamesApplication {
    static int countUsers = 10;

    public static void main(String[] args) throws SQLException, InterruptedException {
        GameApplication.run(countUsers);
    }
}