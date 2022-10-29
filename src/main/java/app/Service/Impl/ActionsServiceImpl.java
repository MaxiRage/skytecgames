package app.Service.Impl;

import app.Repository.UserRepository;
import app.Service.ActionsService;
import app.Service.ClanService;

/**
 * ������ ����� ���������� ��������� �������� �������������
 * ������ ����� ������������ �������� � ����, � ������ ��� �� �����, ���� �� �������, ���� � ������ � �������� ����
 */
public class ActionsServiceImpl implements ActionsService {

    //TODO ������� ����� �� �������� ����� � ������ = ���������

    private UserRepository userRepository;
    private ClanService clanService;
    int countUsers = userRepository.getCountRowsUsersTable(); // TODO ��� ������, �.�. NPE ?


    //TODO ���������� �� ������ �����
    @Override
    public void runGameUser() {
        int counter = countUsers;
        while (counter-- > 0) {
            int randomCountActions = (int) (Math.random() * 10) + 1;
            String nameUser = userRepository.getUserName(countUsers--);
            if (userRepository.checkIsPresentUserInBD(nameUser)) {
                System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " �������� ����");
                clanService.JoiningClan(nameUser);
                System.out.println(actionsUsers(nameUser, randomCountActions));
            } else runGameUser();
        }
    }

    /**
     * ����� ��������� ��������� ��������
     */
    @Override
    public String actionsUsers(String nameUser, int countActions) {
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