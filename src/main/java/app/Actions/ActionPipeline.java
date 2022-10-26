package app.Actions;

import app.Entity.Users;
import app.RunClass;

import java.sql.SQLException;

/**
 * ������ ����� ���������� ��������� �������� �������������
 * ������ ����� ������������ �������� � ����, � ������ ��� �� �����, ���� �� �������, ���� � ������ � �������� ����
 */
public class ActionPipeline extends Thread {
    //TODO ������� ����� �� �������� ����� � ������ = ���������
    int countUsers = RunClass.countUsers;
    @Override
    public void run() {
        int counter = countUsers;
        while (counter-- > 0) {
            new Thread(() -> {
                int randomCountActions = (int) (Math.random() * 10) + 1;
                try {
                    String nameUser = new Users().getUserName(countUsers--);
                    System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " �������� ����"); //TODO ������� ����� �����
                    new JoiningClan().feeTreasury(nameUser);
                    System.out.println(actionsUsers(nameUser, randomCountActions));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    /**
     * ����� ��������� ��������� ��������
     */
    private static String actionsUsers(String nameUser, int countActions) {
        if (countActions <= 0)
            return Thread.currentThread() + " � ������: ������������ " + nameUser + " �������� ���� ��� ��������";
        else {
            --countActions;
            int randNumber = (int) ((Math.random() * 3) + 1);
            switch (randNumber) {
                case 1 ->
                        System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ��� �� �����");
                case 2 ->
                        System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ����� � �������� ����");
                default ->
                        System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ��� �� �������");
            }
            actionsUsers(nameUser, countActions);
        }
        return Thread.currentThread() + " � ������: ������������ " + nameUser + " �������� ���� � ����� ����� ������";
    }
}