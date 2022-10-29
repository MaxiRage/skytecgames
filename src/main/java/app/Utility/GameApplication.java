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

        //        ������ �������������
        while (countUsers-- > 0) {
            Runnable taskCreateUsers = () -> {
                new UserServiceImpl().createNewUser();
            };
            new Thread(taskCreateUsers).start();
        }

//        ������ ����� + �����
//        ClanRepository clanRepository = new ClanRepositoryImpl();
//        final String[] nameClans = {"PowerRangers", "MadWolves", "ForestElves"};
//        Arrays.stream(nameClans)
//                .forEach(nameClan -> clanRepository.createClan(nameClan));

        //������������ ��������� ��������
        //TODO �� ����, ��� ����� ��������� ��� ��� ���� ������ � ��� ����� �� �������� -> ������� �������� ������ �����
//        new ActionPipeline().start();
    }
}