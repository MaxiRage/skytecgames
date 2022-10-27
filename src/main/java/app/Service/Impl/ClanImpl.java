package app.Service.Impl;

import app.Repository.ClanRepository;
import app.Service.Clan;
import app.Utility.EnamActions;
import app.Repository.DetailsRepository;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import lombok.SneakyThrows;

/***
 * ��� ���������� � ���� ������������ ���������� ����� � ���� ������������ ������ �� ���������� 2
 */

public class ClanImpl implements Clan {

    UserRepository userRepository;
    TreasuryRepository treasuryRepository;
    DetailsRepository detailsRepository;
    ClanRepository clanRepository;
    int rateFee = 2;


    // TODO ��������, ��� ���� ��� � �����
    @SneakyThrows
    @Override
    public void JoiningClan(String nameUser) { // TODO ��������� � ��������?
        int randNumberJoiningClan = (int) ((Math.random() * 3) + 1);
        String nameClans1 = "PowerRangers";
        String nameClans2 = "MadWolves";
        String nameClans3 = "ForestElves";

        switch (randNumberJoiningClan) {
            case 1 -> {
                System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ������� � ����" + nameClans1);
            }
            case 2 ->
                    System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ����� � �������� ����");
            default -> System.out.println(Thread.currentThread() + " � ������: ����� " + nameUser + " ��� �� �������");
        }

        int balanceUser = userRepository.getUserBalance(nameUser);
        int amountForJoining = userRepository.getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {
            userRepository.updateBalanceUsers(amountForJoining, nameUser);

            treasuryRepository.updateBalanceTreasury(amountForJoining);

            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.JOININGCLAN), amountForJoining);

        } else System.out.println(Thread.currentThread() + " � ������: " + "�� ������� ������� �� ��������");

        System.out.printf(Thread.currentThread() + " � ������: " +
                        "������������ %s ������� � ���� \"PowerRangers\" � �������� %d � ����� \n"
                , nameUser, amountForJoining);
    }
}
