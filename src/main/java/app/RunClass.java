package app;

import app.Service.Impl.ActionPipeline;
import app.DB.ManagementTables;
import app.Service.Impl.Treasury;
import app.Service.Impl.Users;

import java.sql.SQLException;

public class RunClass {
    /***
     * ������� ���������� ����������� �������������
     */

    //TODO �� �� ����������
    public final static int countUsers = 1;

    public static void main(String[] args) throws SQLException, InterruptedException {
        //Connect and create databases
        ManagementTables createTablesH2 = new ManagementTables();
        createTablesH2.dropAllTable();
        createTablesH2.createAllTables();
//        createTablesH2.truncateAllTable();

        //������ ����� ����� "PowerRangers"
//        new Treasury().start();

        //������ �������������
//        int counter = countUsers;
//        while (counter-- > 0) {
//            new Users().start();
//        }
        //������������ ��������� ��������
        //TODO �� ����, ��� ����� ��������� ��� ��� ���� ������ � ��� ����� �� �������� -> ������� �������� ������ �����
//        new ActionPipeline().start();
    }
}