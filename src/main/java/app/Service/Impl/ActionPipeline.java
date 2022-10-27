package app.Service.Impl;

import app.Repository.UserRepository;
import app.Service.Clan;
import lombok.SneakyThrows;

/**
 * ������ ����� ���������� ��������� �������� �������������
 * ������ ����� ������������ �������� � ����, � ������ ��� �� �����, ���� �� �������, ���� � ������ � �������� ����
 */
public class ActionPipeline extends Thread {
    @Override
    public void run() {
        runGameUser();
    }

    //TODO ������� ����� �� �������� ����� � ������ = ���������
    UserRepository userRepository;
    Clan clan;

    int countUsers = userRepository.getCountRowsUsersTable(); // TODO ��� ������?


    //TODO ���������� �� ������ �����
    @SneakyThrows
    public void runGameUser() {
        int counter = countUsers;
        while (counter-- > 0) {
            int randomCountActions = (int) (Math.random() * 10) + 1;
            String nameUser = userRepository.getUserName(countUsers--);
            if (userRepository.checkIsPresentUserInBD(nameUser)) {
                System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " �������� ����");
                clan.JoiningClan(nameUser);
                System.out.println(actionsUsers(nameUser, randomCountActions));
            } else runGameUser();
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
                case 1 -> System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ��� �� �����");
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