import DB.ManagementTables;
import Entity.Treasury;
import Entity.Users;

import java.sql.SQLException;

public class RunClass {

    public static void main(String[] args) throws SQLException {
        //Connect and create databases
        ManagementTables createTablesH2 = new ManagementTables();
        createTablesH2.dropAllTable();
        createTablesH2.createAllTables();
//        createTablesH2.truncateAllTable();

        //Создаём казну
        new Treasury().start();

        //Создаём 10 юзеров
        int countUsers = 10;

        while (countUsers > 0) {
            new Users().start();
            countUsers--;
        }
    }
}