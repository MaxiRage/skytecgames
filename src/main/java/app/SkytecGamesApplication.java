package app;

import app.Utility.GameApplication;

/***
 * Укажите количество создаваемых пользователей в переменной "countUsers"
 */
public class SkytecGamesApplication {
    static int countUsers = 2;

    public static void main(String[] args) {
        GameApplication.run(countUsers);
    }
}