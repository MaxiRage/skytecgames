package app;

import app.Utility.GameApplication;

/***
 * Specify the number of users to create in the variable "countUsers"
 */
public class SkytecGamesApplication {
    static int countUsers = 2;

    public static void main(String[] args) {
        GameApplication.run(countUsers);
    }
}