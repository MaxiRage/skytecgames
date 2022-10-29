package app.Utility;

import app.DB.ManagementTables;
import app.Service.Impl.UserServiceImpl;

import java.sql.SQLException;

public class GameApplication {
    public static void run(int countUsers) throws SQLException {
        //Connect and create databases
        ManagementTables createTablesH2 = new ManagementTables();
        createTablesH2.dropAllTable();
        createTablesH2.createAllTables();
//        createTablesH2.truncateAllTable();

        //        Создаём пользователей
        while (countUsers-- > 0) {
            Runnable taskCreateUsers = () -> {
                new UserServiceImpl().createNewUser();
            };
            new Thread(taskCreateUsers).start();
        }

//        Создаём кланы + казну
//        ClanRepository clanRepository = new ClanRepositoryImpl();
//        final String[] nameClans = {"PowerRangers", "MadWolves", "ForestElves"};
//        Arrays.stream(nameClans)
//                .forEach(nameClan -> clanRepository.createClan(nameClan));

        //Пользователи выполняют действия
        //TODO до того, как юзеры создаются они уже идут играть и нет денег на членство -> сделать ожидание потока денег
//        new ActionPipeline().start();
    }
}