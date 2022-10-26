package app;

import app.Actions.ActionPipeline;
import app.DB.ManagementTables;
import app.Entity.Treasury;
import app.Entity.Users;

import java.sql.SQLException;

public class RunClass {
    /***
     * ������� ���������� ����������� �������������
     */
    public final static int countUsers = 10;

    public static void main(String[] args) throws SQLException, InterruptedException {
        //Connect and create databases
        ManagementTables createTablesH2 = new ManagementTables();
        createTablesH2.dropAllTable();
        createTablesH2.createAllTables();
//        createTablesH2.truncateAllTable();

        //������ ����� ����� "PowerRangers"
        new Treasury().start();

        //������ �������������
        int counter = countUsers;
        while (counter-- > 0) {
            new Users().start();
        }
        //������������ ��������� ��������
        //TODO �� ����, ��� ����� ��������� ��� ��� ���� ������ � ��� ����� �� �������� -> ������� �������� ������ �����
        new ActionPipeline().start();
    }
}