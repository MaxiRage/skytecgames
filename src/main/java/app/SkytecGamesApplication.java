package app;

import app.Utility.GameApplication;

/***
 * ������� ���������� ����������� ������������� � ���������� "countUsers"
 */
public class SkytecGamesApplication {
    static int countUsers = 2;

    public static void main(String[] args) {
        GameApplication.run(countUsers);
    }
}