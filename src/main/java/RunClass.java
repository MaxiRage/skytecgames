import DB.CreateTablesH2;

import java.sql.SQLException;

public class RunClass {

    public static void main(String[] args) throws SQLException {
        //������������ � ������� ����������� ��
        CreateTablesH2 createTablesH2 = new CreateTablesH2();
        createTablesH2.createAllTables();
//        createTablesH2.dropAllTable();


    }
}
