package app;

import app.Actions.ActionPipeline;
import app.DB.ManagementTables;
import app.Entity.Treasury;
import app.Entity.Users;

import java.sql.SQLException;

public class RunClass {
    /***
     * Укажите количество создаваемых пользователей
     */
    public final static int countUsers = 10;

    public static void main(String[] args) throws SQLException, InterruptedException {
        //Connect and create databases
        ManagementTables createTablesH2 = new ManagementTables();
        createTablesH2.dropAllTable();
        createTablesH2.createAllTables();
//        createTablesH2.truncateAllTable();

        //Создаём казну клана "PowerRangers"
        new Treasury().start();

        //Создаём пользователей
        int counter = countUsers;
        while (counter-- > 0) {
            new Users().start();
        }
        //Пользователи выполняют действия
        //TODO до того, как юзеры создаются они уже идут играть и нет денег на членство -> сделать ожидание потока денег
        new ActionPipeline().start();
    }
}